package com.mobo.mobosubway.data;

import lombok.Data;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

@Data
public class SubwayDataCollection {

    private Map<String, SubwayLine> lines;

    private Map<String, Station> stations;

    public boolean isValid() {
        if (MapUtils.isEmpty(lines)) {
            return false;
        }
        if (MapUtils.isEmpty(stations)) {
            return false;
        }
        return true;
    }
}
