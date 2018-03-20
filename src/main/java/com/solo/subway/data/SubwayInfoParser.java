package com.solo.subway.data;


import com.alibaba.fastjson.JSON;
import com.solo.subway.util.HttpUtil;
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
    private static SubwayInfoParser instance = new SubwayInfoParser();
    private SubwayInfoParser(){}

    public static SubwayInfoParser getInstace() {
        return instance;
    }

    public void parse() throws IOException {
        String result = HttpUtil.httpGet(url);
        System.out.println(result);
        Map<String, Object> json = (Map<String, Object>) JSON.parse(result);

        System.out.println(json);
    }

    public static void main(String args[]) throws IOException {
        SubwayInfoParser.getInstace().parse();
    }
}
