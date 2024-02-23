package com.mobo.mobosubway.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
/**
 * 到某个站点的路线信息
 */
public class PathInfo {

    private static final int MAX = 20000;

    private static final double MAX_DISTANCE = 5000000;

    private String stationId;

    private int length = MAX;

    private double distance = MAX_DISTANCE;

    private int transferNum = -1; //换乘数

    private List<String> detail = new ArrayList<>(); //详细路径

    public void addNodeToPath(String id) {
        detail.add(id);
    }

    public boolean accessible() {
        return length < MAX;
    }
}
