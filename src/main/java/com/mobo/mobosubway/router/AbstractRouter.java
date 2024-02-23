/**
 * @(#)AbstractRouter.java, 4月 22, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mobo.mobosubway.router;

import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.data.Station;
import com.mobo.mobosubway.data.SubwayDataCollection;
import com.mobo.mobosubway.service.impl.QueryDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mobo.mobosubway.util.StationsUtils.transferred;


/**
 * @author lcy362
 */
public abstract class AbstractRouter implements StationRouter{

    @Autowired
    private QueryDataService queryDataService;

    @Override
    public Map<String, PathInfo> pathToAll(String originName) {
        SubwayDataCollection data = queryDataService.querySource();
        Map<String, Station> stations = data.getStations();
        Map<String, PathInfo> knownPath = initKnownPath(stations.values(), originName);
        Map<String, PathInfo> waitingPath = initWaitingPath(stations.values(), originName);
        String originId = getOriginId(stations.values(), originName);

        handleAllPath(knownPath, waitingPath, originId, stations);

        setTransferNums(knownPath, stations);

        return knownPath;
    }

    protected abstract void handleAllPath(Map<String, PathInfo> knownPath, Map<String, PathInfo> waitingPath, String originId, Map<String, Station> stations);

    private Map<String, PathInfo> initKnownPath(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> station.getName().equals(originName))
                .map(Station::getId)
                .collect(Collectors.toMap(id -> id, PathInfoFactory::initKnownPath));
    }

    private Map<String, PathInfo> initWaitingPath(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> !station.getName().equals(originName))
                .map(Station::getId)
                .collect(Collectors.toMap(id -> id, PathInfoFactory::initWaitingPath));
    }

    private String getOriginId(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> station.getName().equals(originName))
                .map(Station::getId).findAny().orElseThrow(IllegalArgumentException::new);
    }

    private void setTransferNums(Map<String, PathInfo> knownPath, Map<String, Station> stations) {
        //一次计算换乘信息
        knownPath.values().stream()
                .filter(PathInfo::accessible)
                .forEach(pathInfo -> pathInfo.setTransferNum(getTransferNumOfPath(pathInfo, stations)));
    }

    private int getTransferNumOfPath(PathInfo pathInfo, Map<String, Station> stations) {
        int transfer = 0;
        List<String> detail = pathInfo.getDetail();

        for (int i = 1; i < detail.size() - 1; i++) {
            Station pre = stations.get(detail.get(i - 1));
            Station next = stations.get(detail.get(i + 1));
            if (transferred(stations.get(detail.get(i)).getLines(), pre.getLines(), next.getLines())) {
                transfer++;
            }
        }
        return transfer;
    }
}
