package com.mobo.mobosubway.service;

import com.mobo.mobosubway.source.SubwaySourceQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AmapQueryServiceTest {

    @Autowired
    private SubwaySourceQueryService queryService;

    @Test
    public void testParse() {
        queryService.parse();
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
