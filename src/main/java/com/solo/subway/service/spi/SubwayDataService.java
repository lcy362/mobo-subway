/**
 * @(#)SubwayDataService.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.service.spi;

import com.solo.subway.data.SubwayDataCollector;

/**
 * @author licy03
 */
public interface SubwayDataService {

    SubwayDataCollector getSubwayData();

    void saveSubwayDataToLocal(SubwayDataCollector data);
}
