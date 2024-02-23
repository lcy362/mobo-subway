package com.mobo.mobosubway.service;

import com.mobo.mobosubway.data.SubwayDataCollection;
import com.mobo.mobosubway.source.SubwaySourceQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryServiceTest {

    @Autowired
    private QueryDataService queryService;

    @Test
    public void testParse() {
        SubwayDataCollection subwayDataCollection = queryService.querySource();
        System.out.println(subwayDataCollection);

    }
}
