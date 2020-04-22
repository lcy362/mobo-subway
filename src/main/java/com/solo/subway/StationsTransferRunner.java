package com.solo.subway;

import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.router.StationRouter;
import com.solo.subway.service.spi.SubwayDataService;
import com.solo.subway.data.PathInfo;
import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StationsTransferRunner implements CommandLineRunner {

    @Autowired
    private SubwayDataService subwayDataService;

    @Autowired
    private StationRouter router;

    @Override
    public void run(String... args) throws Exception {
        SubwayDataCollector subway = subwayDataService.getSubwayData();
        Map<String, Station> stations = subway.getStations();
        Map<String, SubwayLine> lineName = subway.getLineName();

        for (Station station : stations.values()) {
            log.info(station.getName());
            for (String line : station.getLines()) {
                log.info(station.getName() + " in " + lineName.get(line).getName());
            }
            for (String next : station.getNextStations()) {
                log.info(station.getName() + " next to " + stations.get(next).getName());
            }
        }

        Map<String, PathInfo> aolin = router.pathToAll("阜通", stations);
        Map<String, PathInfo> tian = router.pathToAll("天安门东", stations);
        for (Station station : stations.values()) {
            PathInfo aolinPath = aolin.get(station.getId());
            PathInfo tianPath = tian.get(station.getId());
            if (aolinPath.getLength() < 15 && tianPath.getLength() < 7) {
                System.out.println(station.getName() + " " + aolinPath.getLength() + "," + aolinPath.getTransferNum()
                        + "," + tianPath.getLength() + "," + tianPath.getTransferNum());
            }
        }
    }
}

