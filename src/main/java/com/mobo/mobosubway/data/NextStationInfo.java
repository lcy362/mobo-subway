package com.mobo.mobosubway.data;

import lombok.Data;

@Data
public class NextStationInfo {

    private String id;

    private String name;

    private double distance;

    private String line;
}
