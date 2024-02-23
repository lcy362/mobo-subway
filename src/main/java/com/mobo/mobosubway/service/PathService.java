package com.mobo.mobosubway.service;

import com.mobo.mobosubway.vo.PathInfoVO;

import java.util.List;
import java.util.Map;

public interface PathService {

    PathInfoVO getPath(String startName, String endName);

    List<String> getAvailableMeetingPoint(List<String> stationNames, List<Double> distanceLimits);
}
