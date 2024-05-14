package com.mobo.mobosubway.data;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PathNode {

    private String stationId;

    private String stationName;

    private boolean transfer;

}
