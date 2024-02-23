package com.mobo.mobosubway.service;

import com.mobo.mobosubway.vo.PathInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PathServiceTest {

    @Autowired
    private PathService pathService;

    @Test
    public void getPathTest() {
        PathInfoVO path = pathService.getPath("南礼士路", "阜通");
        System.out.println(path);
    }

    @Test
    public void meetingPointTest() {
        List<String> result = pathService.getAvailableMeetingPoint(Arrays.asList("天安门东", "古城"), Arrays.asList(7000d, 15000d));
        System.out.println(result);
    }

}
