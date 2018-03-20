package com.solo.subway.data;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class SubwayInfoParser {
    private static String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";

    public static void parse() throws IOException {
        String result = httpGet();
        System.out.println(result);
        Map<String, Object> json = (Map<String, Object>) JSON.parse(result);

        System.out.println(json);
    }


    private static String httpGet() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse=httpClient.execute(httpGet);
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status == 200) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }
        return null;
    }

    public static void main(String args[]) throws IOException {
        parse();
    }
}
