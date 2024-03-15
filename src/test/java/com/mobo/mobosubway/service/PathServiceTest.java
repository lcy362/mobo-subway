package com.mobo.mobosubway.service;

import com.mobo.mobosubway.proxy.AmapProxy;
import com.mobo.mobosubway.store.MapDBStore;
import com.mobo.mobosubway.vo.PathInfoVO;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PathServiceTest {

    @Autowired
    private PathService pathService;

    @MockBean
    AmapProxy proxy;

    @MockBean
    MapDBStore mapdb;

    @BeforeEach
    public void before() throws IOException {
        String content = FileUtils.readFileToString(new File("src/test/java/com/mobo/mobosubway/service/amap_result.json"), "UTF-8");
        Mockito.when(proxy.getResult()).thenReturn(content);
    }

    @Test
    public void getPathTest() {
        PathInfoVO path = pathService.getPath("南礼士路", "古城");
        Assert.isTrue(path.getDistance() > 0, "Distance should be greater than 0");
        Assert.isTrue(path.getTransferNum() == 0, "no transfer in this route");
    }

    @Test
    public void meetingPointTest() {
        List<String> result = pathService.getAvailableMeetingPoint(Arrays.asList("天安门东", "古城"), Arrays.asList(7000d, 15000d));
        System.out.println(result);
    }

}
