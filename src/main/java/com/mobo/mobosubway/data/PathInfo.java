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

    @Deprecated
    private List<String> detail = new ArrayList<>(); //详细路径

    @Deprecated
    private List<String> pathLine = new ArrayList<>(); //途径线路

    @Deprecated
    private String currentLine; //中间状态，计算路线过程中，当前所在线路

    private List<PathNode> pathDetail = new ArrayList<>();

    public void addNodeToPath(String id) {
        detail.add(id);
    }

    public void addNodeToPath(Station station) {
        detail.add(station.getId());
        PathNode pathNode = new PathNode();
        pathNode.setStationName(station.getName());
        pathNode.setStationId(station.getId());
        pathDetail.add(pathNode);
    }

    public boolean accessible() {
        return length < MAX || distance < MAX_DISTANCE;
    }
}
