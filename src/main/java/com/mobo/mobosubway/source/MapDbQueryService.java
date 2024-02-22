package com.mobo.mobosubway.source;

import com.mobo.mobosubway.data.Station;
import com.mobo.mobosubway.data.SubwayDataCollection;
import com.mobo.mobosubway.data.SubwayLine;
import com.mobo.mobosubway.store.MapDBStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.mobo.mobosubway.util.FoxSubwayConstants.LINE_TAG;
import static com.mobo.mobosubway.util.FoxSubwayConstants.STATION_TAG;

@Service
@Order(1)
@Slf4j
@AllArgsConstructor
public class MapDbQueryService implements SubwaySourceQueryService{

    private final MapDBStore mapdb;

    @Override
    public SubwayDataCollection parse() {
        Map<String, SubwayLine> lineName = mapdb.load(LINE_TAG);
        Map<String, Station> stations = mapdb.load(STATION_TAG);

        log.info("load from mapdb " + lineName.size() + " " + stations.size());
        mapdb.close();
        return new SubwayDataCollection(lineName, stations);
    }

}
