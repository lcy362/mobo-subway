/**
 * @(#)StationRouter.java, 3月 20, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.router;

import com.solo.subway.data.Station;
import com.solo.subway.data.PathInfo;

import java.util.Map;

/**
 * @author lcy362
 */
public interface StationRouter {

    Map<String, PathInfo> pathToAll(String originName, Map<String, Station> stations);
}
