/**
 * @(#)SubwayDataCollector.java, Mar 10, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * @author licy03
 */
@Data
@AllArgsConstructor
public class SubwayDataCollector {

    private Map<String, SubwayLine> lineName;

    private Map<String, Station> stations;

    public boolean isValid() {
        if (MapUtils.isEmpty(lineName)) {
            return false;
        }
        if (MapUtils.isEmpty(stations)) {
            return false;
        }
        return true;
    }

}