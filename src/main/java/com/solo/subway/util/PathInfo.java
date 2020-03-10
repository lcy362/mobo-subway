package com.solo.subway.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
/**
 * 到某个站点的路线信息
 */
public class PathInfo {

    private String stationId;

    private int length;

    private int transferNum; //换乘数

    private List<String> detail = new ArrayList<>(); //详细路径

    public void addNodeToPath(String id) {
        detail.add(id);
    }
}
