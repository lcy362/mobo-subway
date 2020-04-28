package com.solo.subway;

import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.router.StationRouter;
import com.solo.subway.service.spi.SubwayDataService;
import com.solo.subway.data.PathInfo;
import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class StationsMeetingPointRunner implements CommandLineRunner {

    private final SubwayDataService subwayDataService;

    private final StationRouter router;

    @Value("${stationNames:天安门东,阜通}")
    private List<String> stationNames;

    @Value("${stationDistances:7,15}")
    private List<Integer> stationDistances;

    @Override
    public void run(String... args) throws Exception {
        if (stationNames.size() != stationDistances.size()) {
            throw new IllegalArgumentException("stationNames和stationDistances的个数需要一致");
        }
        SubwayDataCollector subway = subwayDataService.getSubwayData();
        Map<String, Station> stations = subway.getStations();

        List<Map<String, PathInfo>> pathInfos =
                stationNames.stream().map(station -> router.pathToAll(station, stations)).collect(Collectors.toList());

        for (Station station : stations.values()) {
            boolean matched = true;
            for (int i = 0; i < stationNames.size(); i++) {
                Map<String, PathInfo> pathInfoMap = pathInfos.get(i);
                PathInfo pathInfo = pathInfoMap.get(station.getId());
                if (pathInfo.getLength() >= stationDistances.get(i)) {
                    matched = false;
                }
            }
            if (matched) {
                log.info("{} match", station.getName());
            }
        }
    }
}

