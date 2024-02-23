package com.mobo.mobosubway.vo;

import lombok.Data;

import java.util.List;

@Data
public class PathInfoVO {

    private String stationName;

    private int length;

    private double distance;

    private List<String> detail;

    private int transferNum;
}
