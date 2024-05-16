package com.mobo.mobosubway.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class NextStationInfo implements Serializable {

    private String id;

    private String name;

    private double distance;

    private String line;
}
