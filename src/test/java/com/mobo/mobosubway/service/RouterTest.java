package com.mobo.mobosubway.service;

import com.mobo.mobosubway.data.PathInfo;
import com.mobo.mobosubway.router.StationRouter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class RouterTest {

    @Autowired
    @Qualifier("dijkstraDistanceRouter")
    private StationRouter stationRouter;


    @Test
    public void test() {
        Map<String, PathInfo> result = stationRouter.pathToAll("南礼士路");
        // 打印结果
        result.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
