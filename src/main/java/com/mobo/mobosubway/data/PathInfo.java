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

    private int transferNum = -1; //换乘数

    @Deprecated
    private List<String> detail = new ArrayList<>(); //详细路径

    @Deprecated
    private List<String> pathLine = new ArrayList<>(); //途径线路

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
        pathNode.setStationLines(new ArrayList<>(station.getLines()));

        PathNode previous = pathDetail.get(0);

        if (StringUtils.isBlank(currentLine)) {
            //初始站逻辑，根据前两站所在线路确定初始线路
            List<String> intersectionSet = ListUtils.intersection(previous.getStationLines(), pathNode.getStationLines());
            if (CollectionUtils.isNotEmpty(intersectionSet)) {
                currentLine = intersectionSet.get(0);
            }
        } else {

        }

        pathDetail.add(pathNode);
    }

    public boolean accessible() {
        return length < MAX || distance < MAX_DISTANCE;
    }
}
