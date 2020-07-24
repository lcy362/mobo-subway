/**
 * @(#)PathRunner.java, 7月 24, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway;

import com.solo.subway.data.PathInfo;
import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.router.StationRouter;
import com.solo.subway.service.spi.SubwayDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author licy03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PathRunner implements CommandLineRunner {

    private final SubwayDataService subwayDataService;

    private final StationRouter router;

    @Value("${startName:王府井}")
    private String startName;

    @Value("${endName:阜通}")
    private String endName;

    @Override
    public void run(String... args) throws Exception {
        SubwayDataCollector subway = subwayDataService.getSubwayData();
        Map<String, Station> stationsById = subway.getStations();
        Map<String, PathInfo> pathInfoMap = router.pathToAll(startName, stationsById);
        Map<String, Station> stationsByName = stationsById.values().stream().collect(Collectors.toMap(Station::getName, s -> s));
        String endId = stationsByName.get(endName).getId();
        PathInfo path = pathInfoMap.get(endId);
        log.info("{} to {}, path detail {}", startName, endName, getPathDetailDescription(path.getDetail(), stationsById));
    }

    private String getPathDetailDescription(List<String> detail, Map<String, Station> stationsById) {
        return detail.stream().map(stationsById::get).map(Station::getName).collect(Collectors.joining("->"));
    }
}
