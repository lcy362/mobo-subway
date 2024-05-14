package com.mobo.mobosubway.data;

import com.mobo.mobosubway.util.GeoUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Station implements Serializable {

    private String id;

    private String name;

    /**
     * 所在线路
     */
    private Set<String> lines = new HashSet<>();

    private double longitude;

    private double latitude;

    private String pinyin;

    private Map<String, NextStationInfo> nextStations = new HashMap<>();

    public void addLine(String line) {
        lines.add(line);
    }

    public void addNextStation(Station station, String line) {
        double distance = GeoUtils.getDistance(this.getLatitude(), this.getLongitude(), station.getLatitude(), station.getLongitude());

        NextStationInfo nextStationInfo = new NextStationInfo();
        nextStationInfo.setId(station.getId());
        nextStationInfo.setDistance(distance);
        nextStationInfo.setName(station.getName());
        nextStationInfo.setLine(line);

        nextStations.put(nextStationInfo.getId(), nextStationInfo);
    }

}
