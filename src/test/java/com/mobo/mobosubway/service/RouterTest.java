package com.mobo.mobosubway.service;

import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.router.StationRouter;
import com.mobo.mobosubway.service.impl.PathDebugService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class RouterTest {

    @Autowired
    private PathDebugService pathDebugService;


    @Test
    public void test() {
        pathDebugService.printPathInfo("南礼士路");
    }
}
