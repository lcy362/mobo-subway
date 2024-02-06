package com.mobo.mobosubway.data;

import lombok.Data;

@Data
public class SubwayLine {

    private String id;

    private String name;

    /**
     * 是否环线
     */
    private boolean isCircle;
}
