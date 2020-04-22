/**
 * @(#)PathInfoFactory.java, 4月 22, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.factory;

import com.solo.subway.data.PathInfo;

/**
 * @author lcy362
 */
public class PathInfoFactory {

    public static PathInfo initKnownPath(String id) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setLength(0);
        pathInfo.setStationId(id);
        pathInfo.addNodeToPath(id);
        return pathInfo;
    }

    public static PathInfo initWaitingPath(String id) {
        PathInfo pathInfo = new PathInfo();
        pathInfo.setStationId(id);
        return pathInfo;
    }
}