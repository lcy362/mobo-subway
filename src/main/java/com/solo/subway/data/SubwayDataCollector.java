/**
 * @(#)SubwayDataCollector.java, Mar 10, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.data;

import lombok.Data;

import java.util.Map;

/**
 * @author licy03
 */
@Data
public class SubwayDataCollector {

    private Map<String, SubwayLine> lineName;

    private Map<String, Station> stations;
}