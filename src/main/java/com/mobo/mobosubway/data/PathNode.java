package com.mobo.mobosubway.data;

import lombok.Data;

import java.util.List;

@Data
public class PathNode {

    private String stationId;

    private String stationName;

    private List<String> line;

    private boolean transfer;

}
