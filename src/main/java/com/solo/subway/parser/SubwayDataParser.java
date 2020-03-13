/**
 * @(#)SubayDataParserService.java, 3月 13, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.solo.subway.parser;

import com.solo.subway.data.SubwayDataCollector;

import java.io.IOException;

/**
 * @author licy03
 */
public interface SubwayDataParser {

    SubwayDataCollector parse() throws IOException;
}
