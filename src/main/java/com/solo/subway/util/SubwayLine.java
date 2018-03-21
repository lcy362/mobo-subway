package com.solo.subway.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubwayLine implements Serializable{
    private String id;
    private String name;
    private boolean isCircle;
}
