package com.mobo.mobosubway.service.impl;

import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.data.Station;
import com.mobo.mobosubway.router.StationRouter;
import com.mobo.mobosubway.service.PathService;
import com.mobo.mobosubway.vo.PathInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PathServiceImpl implements PathService {

    private final StationRouter stationRouter;

    private final QueryDataService queryDataService;

    @Override
    public PathInfoVO getPath(String startName, String endName) {
        Map<String, PathInfo> pathInfoMap = stationRouter.pathToAll(startName);
        Map<String, Station> stationsById = queryDataService.querySource().getStations();
        Map<String, Station> stationsByName = stationsById.values().stream().collect(Collectors.toMap(Station::getName, s -> s));
        String endId = stationsByName.get(endName).getId();
        PathInfo path = pathInfoMap.get(endId);
        PathInfoVO pathInfoVO = wrapPathVO(path, stationsById);
        log.info("PathInfoVO: {}", pathInfoVO);
        return pathInfoVO;
    }

    @Override
    public List<String> getAvailableMeetingPoint(List<String> stationNames, List<Double> distanceLimits) {
        if (stationNames.size() != distanceLimits.size()) {
            return null;
        }
        Map<String, Station> stations = queryDataService.querySource().getStations();
        List<Map<String, PathInfo>> pathInfos =
                stationNames.stream().map(stationRouter::pathToAll).toList();

        List<String> availableMeetingPoints = new ArrayList<>();
        for (Station station : stations.values()) {
            boolean matched = true;
            for (int i = 0; i < stationNames.size(); i++) {
                Map<String, PathInfo> pathInfoMap = pathInfos.get(i);
                PathInfo pathInfo = pathInfoMap.get(station.getId());
                if (pathInfo.getDistance() >= distanceLimits.get(i)) {
                    matched = false;
                }
            }
            if (matched) {
                availableMeetingPoints.add(station.getName());
            }
        }
        return availableMeetingPoints;
    }

    private PathInfoVO wrapPathVO(PathInfo pathInfo, Map<String, Station> stationsById) {
        if  (pathInfo == null) {
            return null;
        }
        // 封装VO
        PathInfoVO pathInfoVO = new PathInfoVO();
        pathInfoVO.setDistance(pathInfo.getDistance());
        pathInfoVO.setLength(pathInfoVO.getLength());
        pathInfoVO.setStationName(stationsById.get(pathInfo.getStationId()).getName());
        pathInfoVO.setDetail(pathInfo.getDetail().stream().map(id -> stationsById.get(id).getName()).toList());
        pathInfoVO.setTransferNum(pathInfo.getTransferNum());
        return pathInfoVO;
    }
}
