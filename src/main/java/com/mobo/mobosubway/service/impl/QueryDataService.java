package com.mobo.mobosubway.service.impl;

import com.mobo.mobosubway.data.SubwayDataCollection;
import com.mobo.mobosubway.source.SubwaySourceQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QueryDataService {

    private final List<SubwaySourceQueryService> queryServiceList;

    public SubwayDataCollection querySource() {
        //依次查询所有数据源，查到数据后即止
        for (SubwaySourceQueryService queryService : queryServiceList) {
            SubwayDataCollection subwayDataCollection = queryService.parse();
            if (subwayDataCollection != null && subwayDataCollection.isValid()){
                log.info("查询到数据，数据源为：{}", queryService.getClass().getSimpleName());
                return subwayDataCollection;
            }
        }
        return null;

    }
}
