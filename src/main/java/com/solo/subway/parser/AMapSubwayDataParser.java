/**
 * @(#)AMapSubwayDataParser.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.data.SubwayLine;
import com.solo.subway.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lcy362
 */
@Service
@Order(2)
@Slf4j
public class AMapSubwayDataParser implements SubwayDataParser {

    private final String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";

    @Override
    public SubwayDataCollector parse() throws IOException {
        Map<String, SubwayLine> lineName = new HashMap<>();
        Map<String, Station> stations = new HashMap<>();
        String result = HttpUtil.httpGet(url);
        log.debug("http info " + result);
        Map<String, Object> json = (Map<String, Object>) JSON.parse(result);

        JSONArray lines = (JSONArray) json.get("l");
        Iterator iterator = lines.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> line = (Map<String, Object>) iterator.next();
            log.debug("handle subwayline " + line);
            SubwayLine subwayLine = new SubwayLine();
            subwayLine.setId(line.get("ls").toString());
            subwayLine.setName(line.get("ln").toString());
            if (line.get("lo").equals("1")) {
                subwayLine.setCircle(true);
            } else {
                subwayLine.setCircle(false);
            }
            lineName.put(line.get("ls").toString(), subwayLine);

            JSONArray lineStations = (JSONArray) line.get("st");
            parseStation(lineStations, subwayLine.isCircle(), stations);
        }
        return new SubwayDataCollector(lineName, stations);
    }

    private void parseStation(JSONArray lineStations, boolean isCircleLine, Map<String, Station> stations) {
        Iterator iterator = lineStations.iterator();
        Station head = null;
        Station previous = null;
        while (iterator.hasNext()) {
            Map<String, String> station = (Map<String, String>) iterator.next();
            log.info("handle station " + station);
            Station station1 = stations.get(station.get("poiid"));

            if (station1 == null) {
                station1 = new Station();
            }
            station1.setId(station.get("poiid"));
            station1.setName(station.get("n"));
            station1.setPinyin(station.get("sp"));
            String[] position = station.get("sl").split(",");
            station1.setLongitude(Double.parseDouble(position[0]));
            station1.setLatitude(Double.parseDouble(position[1]));
            String[] staionLines = station.get("r").split("\\|");
            for (String l : staionLines) {
                station1.addLine(l);
            }
            if (previous != null) {
                previous.addStation(station1.getId());
                station1.addStation(previous.getId());
            }

            stations.put(station1.getId(), station1);

            if (head == null) {
                head = station1;
            }

            previous = station1;

        }
        if (isCircleLine && head != null && previous != null) {
            previous.addStation(head.getId());
            head.addStation(previous.getId());
        }
    }
}