package com.solo.subway.path;

import com.solo.subway.util.PathInfo;
import com.solo.subway.util.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    static Logger logger = LoggerFactory.getLogger(Dijkstra.class);
    private static final int MAX = 20000;
    public static Map<String, PathInfo> pathToAll(String originName, Map<String, Station> stations) {
        String originId = null;
        Map<String, PathInfo> knownPath = new HashMap<>();
        Map<String, PathInfo> waitingPath = new HashMap<>();
        for (Station station : stations.values()) {
            PathInfo path = new PathInfo();
            if (station.getName().equals(originName)) {
                originId = station.getId();
                path.setLength(0);
                path.setStationId(originId);
                path.addNodeToPath(originId);
                knownPath.put(originId, path);
            } else {
                path.setLength(MAX);
                path.setStationId(station.getId());
                waitingPath.put(station.getId(), path);
            }
        }
        logger.info("origin id: " + originId);

        PathInfo now = knownPath.get(originId);
        while (now != null) {
            Station currentStation = stations.get(now.getStationId());
            int nextLength = now.getLength() + 1;
            for (String nextId : currentStation.getNextStations()) {
                if (knownPath.containsKey(nextId)) {
                    continue;
                }
                PathInfo nextPath = waitingPath.get(nextId);
                if (nextPath.getLength() > nextLength) {
                    nextPath.setLength(nextLength);
                    logger.info("set " + stations.get(nextId).getName() + " distance to " + nextLength);
                    nextPath.setDetail(now.getDetail());
                    nextPath.addNodeToPath(nextId);
                }
            }
            now = null;
            for (PathInfo pathInfo : waitingPath.values()) {
                if (now == null) {
                    now = pathInfo;
                } else {
                    if (pathInfo.getLength() < now.getLength()) {
                        now = pathInfo;
                    }
                }
            }
            if (now != null) {
                waitingPath.remove(now.getStationId());
                knownPath.put(now.getStationId(), now);
                logger.info(stations.get(now.getStationId()).getName() + " distance is " + now.getLength());
            }

        }

        return knownPath;

    }
}
