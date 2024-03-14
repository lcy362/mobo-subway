package com.mobo.mobosubway.service;

import com.mobo.mobosubway.store.MapDBStore;
import com.mobo.mobosubway.vo.PathInfoVO;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PathServiceTest {

    @Autowired
    private PathService pathService;

    @Mock
    CloseableHttpClient httpClient;

    @MockBean
    MapDBStore mapdb;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPathTest() {
        Mockito.mockStatic(HttpClientBuilder.class);

        PathInfoVO path = pathService.getPath("南礼士路", "阜通");
        System.out.println(path);
    }

    @Test
    public void meetingPointTest() {
        List<String> result = pathService.getAvailableMeetingPoint(Arrays.asList("天安门东", "古城"), Arrays.asList(7000d, 15000d));
        System.out.println(result);
    }

}
