package com.solo.subway.data;

import com.solo.subway.util.GeoUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Station implements Serializable{

    private String id;

    private String name;

    private Set<String> lines = new HashSet<String>(); //所在线路

    private double longitude;

    private double latitude;

    private String pinyin;

    private Set<String> nextStations = new HashSet<String>(); //相邻站点

    private Map<String, Double> nextStationDistance = new HashMap<>();

    public void addLine(String line) {
        lines.add(line);
    }

    public void addNextStation(Station station) {
        nextStations.add(station.getId());
        double distance = GeoUtils.getDistance(this.getLatitude(), this.getLongitude(), station.getLatitude(), station.getLongitude());
        nextStationDistance.put(station.getId(), distance);
    }

}
