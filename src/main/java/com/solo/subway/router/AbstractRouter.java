/**
 * @(#)AbstractRouter.java, 4月 22, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.router;

import com.solo.subway.data.Station;
import com.solo.subway.data.PathInfo;
import com.solo.subway.factory.PathInfoFactory;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lcy362
 */
public abstract class AbstractRouter implements StationRouter{

    @Override
    public abstract Map<String, PathInfo> pathToAll(String originName, Map<String, Station> stations);

    protected Map<String, PathInfo> initKnownPath(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> station.getName().equals(originName))
                .map(Station::getId)
                .collect(Collectors.toMap(id -> id, PathInfoFactory::initKnownPath));
    }

    protected Map<String, PathInfo> initWaitingPath(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> !station.getName().equals(originName))
                .map(Station::getId)
                .collect(Collectors.toMap(id -> id, PathInfoFactory::initWaitingPath));
    }

    protected String getOriginId(Collection<Station> stations, String originName) {
        return stations.stream().filter(station -> station.getName().equals(originName))
                .map(Station::getId).findAny().orElseThrow(IllegalArgumentException::new);
    }
}