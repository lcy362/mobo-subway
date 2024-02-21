package com.mobo.mobosubway.source;



import com.alibaba.fastjson.JSON;
import com.mobo.mobosubway.data.SubwayDataCollection;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Order(2)
@Slf4j
public class AmapQueryService implements SubwaySourceQueryService{

    private final String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";

    HttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    public SubwayDataCollection parse() {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse= null;
        try {
            httpResponse = httpClient.execute(httpGet);

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                return null;
            }
            String resp = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(resp);
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    private SubwayDataCollection parseResp(String resp) {
        Map<String, Object> json = (Map<String, Object>) JSON.parse(resp);
        System.out.println(json);
        SubwayDataCollection subwayData = new SubwayDataCollection();
        return subwayData;
    }
}
