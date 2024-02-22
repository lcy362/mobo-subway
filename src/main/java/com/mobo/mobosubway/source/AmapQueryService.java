package com.mobo.mobosubway.source;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mobo.mobosubway.data.Station;
import com.mobo.mobosubway.data.SubwayDataCollection;
import com.mobo.mobosubway.data.SubwayLine;
import com.mobo.mobosubway.store.MapDBStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@Order(2)
@Slf4j
public class AmapQueryService implements SubwaySourceQueryService{

    private final String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";

    @Autowired
    private MapDBStore mapdb;
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
            return parseResp(resp);
        } catch (IOException e) {
            return null;
        }
    }

    private SubwayDataCollection parseResp(String resp) {
        Map<String, Object> json = (Map<String, Object>) JSON.parse(resp);
        Map<String, SubwayLine> lineName = new HashMap<>();
        Map<String, Station> stations = new HashMap<>();
        JSONArray lines = (JSONArray) json.get("l");
        Iterator iterator = lines.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> line = (Map<String, Object>) iterator.next();
            log.debug("handle subwayline " + line);
            SubwayLine subwayLine = new SubwayLine();
            subwayLine.setId(line.get("ls").toString());
            subwayLine.setName(line.get("ln").toString());
            if (line.get("lo").equals("1")) {
                subwayLine.setCircle(true);
            } else {
                subwayLine.setCircle(false);
            }
            lineName.put(line.get("ls").toString(), subwayLine);

            JSONArray lineStations = (JSONArray) line.get("st");
            parseStation(lineStations, subwayLine.isCircle(), stations);
        }
        SubwayDataCollection subwayData = new SubwayDataCollection();
        subwayData.setLines(lineName);
        subwayData.setStations(stations);
        return subwayData;
    }

    private void parseStation(JSONArray lineStations, boolean isCircleLine, Map<String, Station> stations) {
        Iterator iterator = lineStations.iterator();
        Station head = null;
        Station previous = null;
        while (iterator.hasNext()) {
            Map<String, String> station = (Map<String, String>) iterator.next();
            log.info("handle station " + station);
            Station station1 = stations.get(station.get("poiid"));

            if (station1 == null) {
                station1 = new Station();
            }
            station1.setId(station.get("poiid"));
            station1.setName(station.get("n"));
            station1.setPinyin(station.get("sp"));
            String[] position = station.get("sl").split(",");
            station1.setLongitude(Double.parseDouble(position[0]));
            station1.setLatitude(Double.parseDouble(position[1]));
            String[] staionLines = station.get("r").split("\\|");
            for (String l : staionLines) {
                station1.addLine(l);
            }
            if (previous != null) {
                previous.addNextStation(station1);
                station1.addNextStation(previous);
            }

            stations.put(station1.getId(), station1);

            if (head == null) {
                head = station1;
            }

            previous = station1;

        }
        if (isCircleLine && head != null && previous != null) {
            previous.addNextStation(head);
            head.addNextStation(previous);
        }
    }
}
