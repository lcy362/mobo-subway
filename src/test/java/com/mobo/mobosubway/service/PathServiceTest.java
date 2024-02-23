package com.mobo.mobosubway.service;

import com.mobo.mobosubway.vo.PathInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PathServiceTest {

    @Autowired
    private PathService pathService;

    @Test
    public void getPathTest() {
        PathInfoVO path = pathService.getPath("南礼士路", "阜通");
        System.out.println(path);
    }

}
