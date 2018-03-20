package com.solo.subway.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    public static String httpGet(String url) throws IOException {
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
}
