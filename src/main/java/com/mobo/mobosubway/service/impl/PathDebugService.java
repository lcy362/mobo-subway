package com.mobo.mobosubway.service.impl;

import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.data.Station;
import com.mobo.mobosubway.router.StationRouter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PathDebugService {

    private final StationRouter stationRouter;

    private final QueryDataService queryDataService;

    public void printPathInfo(String station) {
        Map<String, Station> stations = queryDataService.querySource().getStations();
        Map<String, PathInfo> pathInfoMap = stationRouter.pathToAll(station);

        //遍历pathInfoMap
        for (Map.Entry<String, PathInfo> entry : pathInfoMap.entrySet()) {
            String key = entry.getKey();
            String dest = stations.get(key).getName();
            List<String> pathDetail = entry.getValue().getDetail().stream().map(id -> stations.get(id).getName()).toList();
//            log.info("从{}到{}的路径为：{}", station, dest, pathDetail);
        }
    }
}
