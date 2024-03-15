package com.mobo.mobosubway.proxy;

import com.mobo.mobosubway.data.SubwayDataCollection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.mobo.mobosubway.util.FoxSubwayConstants.LINE_TAG;
import static com.mobo.mobosubway.util.FoxSubwayConstants.STATION_TAG;

@Service
@Slf4j
public class AmapProxy {

    private final String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";

    @Autowired
    HttpClient httpClient;

    public String getResult() {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse= null;
        try {
            httpResponse = httpClient.execute(httpGet);

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                return null;
            }
            String resp = EntityUtils.toString(httpResponse.getEntity());
            return resp;
        } catch (IOException e) {
            return null;
        }
    }
}
