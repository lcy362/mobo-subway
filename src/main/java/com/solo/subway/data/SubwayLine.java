package com.solo.subway.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubwayLine implements Serializable{

    private String id;

    private String name;

    //是否环线
    private boolean isCircle;
}
