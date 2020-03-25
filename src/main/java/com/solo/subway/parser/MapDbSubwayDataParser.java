/**
 * @(#)MapDbSubwayDataParser.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.parser;

import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.data.SubwayLine;
import com.solo.subway.store.MapDBTool;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.solo.subway.util.FoxSubwayConstants.LINE_TAG;
import static com.solo.subway.util.FoxSubwayConstants.STATION_TAG;

/**
 * @author licy03
 */
@Service
@Slf4j
@Order(1)
public class MapDbSubwayDataParser implements SubwayDataParser {

    @Override
    public SubwayDataCollector parse() throws IOException {
        //直接从本地mapdb中获取数据

        MapDBTool mapdb = new MapDBTool();

        Map<String, SubwayLine> lineName = mapdb.load(LINE_TAG);
        Map<String, Station> stations = mapdb.load(STATION_TAG);

        log.info("load from mapdb " + lineName.size() + " " + stations.size());
        mapdb.close();
        return new SubwayDataCollector(lineName, stations);
    }
}