package com.solo.subway.util;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Station {
    private String id;
    private String name;
    private Set<String> lines = new HashSet<String>();
    private String position;
    private String pinyin;
    private Set<String> nextStations = new HashSet<String>();

    public void addLine(String line) {
        lines.add(line);
    }

    public void addStation(String station) {
        nextStations.add(station);
    }

}
