package com.mobo.mobosubway.service;

import com.mobo.mobosubway.vo.PathInfoVO;

import java.util.Map;

public interface PathService {

    PathInfoVO getPath(String startName, String endName);
}
