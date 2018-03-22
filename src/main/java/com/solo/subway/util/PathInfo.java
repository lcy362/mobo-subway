package com.solo.subway.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PathInfo {
    private String stationId;
    private int length;
    private List<String> detail = new ArrayList<>();

    public void addNodeToPath(String id) {
        detail.add(id);
    }
}
