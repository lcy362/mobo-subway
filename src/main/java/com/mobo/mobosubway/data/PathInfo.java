package com.mobo.mobosubway.data;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private int transferNum = 0; //换乘数

    @Deprecated
    private List<String> detail = new ArrayList<>(); //详细路径

    private List<String> pathLine = new ArrayList<>(); //途径线路

    private String currentLine; //中间状态，计算路线过程中，当前所在线路

    private List<PathNode> pathDetail = new ArrayList<>();

    @Deprecated
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

    public void addNodeToPath(NextStationInfo station) {
        detail.add(station.getId());
        PathNode pathNode = new PathNode();
        pathNode.setStationName(station.getName());
        pathNode.setStationId(station.getId());
        if (StringUtils.isNotBlank(currentLine) && !StringUtils.equals(station.getLine(), currentLine)) {
            transferNum++;
            pathNode.setTransfer(true);
        }
        if (!StringUtils.equals(station.getLine(), currentLine)) {
            pathLine.add(station.getLine());
        }
        currentLine = station.getLine();

        pathDetail.add(pathNode);
    }

    public boolean accessible() {
        return length < MAX || distance < MAX_DISTANCE;
    }
}
