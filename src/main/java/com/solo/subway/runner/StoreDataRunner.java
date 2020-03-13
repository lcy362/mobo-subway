/**
 * @(#)StoreDataRunner.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.runner;

import com.solo.subway.data.SubwayDataCollector;
import com.solo.subway.service.spi.SubwayDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author licy03
 */
@Service
@Slf4j
@Order(1)
public class StoreDataRunner implements CommandLineRunner {

    @Autowired
    SubwayDataService dataService;

    @Override
    public void run(String... args) throws Exception {
        SubwayDataCollector data = dataService.getSubwayData();
        dataService.saveSubwayDataToLocal(data);
    }
}