/**
 * @(#)SubwayDataServiceImpl.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.service;

import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.parser.SubwayDataParser;
import com.solo.subway.service.spi.SubwayDataService;
import com.solo.subway.store.MapDBTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.solo.subway.util.FoxSubwayConstants.LINE_TAG;
import static com.solo.subway.util.FoxSubwayConstants.STATION_TAG;

/**
 * @author licy03
 */
@Service
@Slf4j
public class SubwayDataServiceImpl implements SubwayDataService {

    @Autowired
    List<SubwayDataParser> parsers;

    @Override
    public SubwayDataCollector getSubwayData() {
        for (SubwayDataParser parser : parsers) {
            try {
                SubwayDataCollector data = parser.parse();
                if (data != null && data.isValid()) {
                    log.info("get data from {}", parser.getClass());
                    return data;
                }
            } catch (IOException e) {
                log.error("{} parse error", parser.getClass(), e);
            }
        }
        return null;
    }

    @Override
    public void saveSubwayDataToLocal(SubwayDataCollector data) {
        if (data == null) {
            return;
        }
        if (!data.isValid()) {
            return;
        }
        DB store = DBMaker.fileDB("file.db").make();
        MapDBTool mapdb = new MapDBTool(store);
        if (MapUtils.isNotEmpty(data.getLineName())) {
            mapdb.save(LINE_TAG, data.getLineName());
            log.info("save lines");
        }
        if (MapUtils.isNotEmpty(data.getStations())) {
            mapdb.save(STATION_TAG, data.getStations());
            log.info("save stations");
        }
        store.close();
    }
}