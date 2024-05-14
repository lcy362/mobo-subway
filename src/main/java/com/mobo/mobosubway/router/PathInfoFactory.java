/**
 * @(#)PathInfoFactory.java, 4月 22, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mobo.mobosubway.router;


import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.data.Station;

/**
 * @author lcy362
 */
public class PathInfoFactory {

    @Deprecated
    public static PathInfo initKnownPath(String id) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setLength(0);
        pathInfo.setDistance(0);
        pathInfo.setStationId(id);
        pathInfo.addNodeToPath(id);
        return pathInfo;
    }

    public static PathInfo initKnownPath(Station station) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setLength(0);
        pathInfo.setDistance(0);
        pathInfo.setStationId(station.getId());
        pathInfo.addNodeToPath(station);
        return pathInfo;
    }

    @Deprecated
    public static PathInfo initWaitingPath(String id) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setStationId(id);
        return pathInfo;
    }

    public static PathInfo initWaitingPath(Station station) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setStationId(station.getId());
        return pathInfo;
    }
}
