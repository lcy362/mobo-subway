package com.solo.subway.data;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
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

    public void addLine(String line) {
        lines.add(line);
    }

    public void addStation(String station) {
        nextStations.add(station);
    }

}
