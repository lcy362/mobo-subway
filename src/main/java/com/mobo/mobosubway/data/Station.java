package com.mobo.mobosubway.data;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Station {

    private String id;

    private String name;

    /**
     * 所在线路
     */
    private Set<String> lines = new HashSet<>();

    private double longitude;

    private double latitude;

    private String pinyin;

    /**
     * 相邻站点
     */
    private Set<String> nextStations = new HashSet<>();

    private Map<String, Double> nextStationDistance = new HashMap<>();
}
