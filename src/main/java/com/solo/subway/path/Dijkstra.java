package com.solo.subway.path;

import com.solo.subway.util.PathInfo;
import com.solo.subway.data.Station;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
            //从起点开始处理
            Station currentStation = stations.get(now.getStationId());
            int nextLength = now.getLength() + 1;
            for (String nextId : currentStation.getNextStations()) {
                if (knownPath.containsKey(nextId)) {
                    continue;
                }
                PathInfo nextPath = waitingPath.get(nextId);
                //更新相邻站点的最短距离
                if (nextPath.getLength() > nextLength) {
                    nextPath.setLength(nextLength);
                    logger.info("set " + stations.get(nextId).getName() + " distance to " + nextLength);
                    nextPath.setDetail(new ArrayList<>(now.getDetail()));
                    nextPath.addNodeToPath(nextId);
                }
            }
            now = null;
            //选出离当前站点距离最小的站，作为下一个要处理的站点
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

        //一次计算换乘信息
        for (PathInfo pathInfo : knownPath.values()) {
            if (pathInfo.getLength() >= MAX) {
                pathInfo.setTransferNum(-1);
                continue;
            }
            int transfer = 0;
            List<String> detail = pathInfo.getDetail();

            for (int i = 1; i < detail.size() - 1; i++) {
                Station pre = stations.get(detail.get(i - 1));
                Station next = stations.get(detail.get(i + 1));
                if (transferd(stations.get(detail.get(i)).getLines(), pre.getLines(), next.getLines())) {
                    transfer++;
                }
            }
            pathInfo.setTransferNum(transfer);
            logger.info(originName + " to " + stations.get(pathInfo.getStationId()).getName()
             + " transfer " + transfer);
        }

        return knownPath;

    }

    /**
     * 输入两个站点的所在线路，判断是否换乘过
     */
    private static boolean transferd(Set<String> currentLine, Set<String> previous, Set<String> next) {
        Set<String> union = new HashSet<>();
        union.addAll(previous);
        union.addAll(next);
        if (union.size() == previous.size() + next.size()) {
            return true;
        }
        Collection<String> intersection = CollectionUtils.intersection(previous, next);
        boolean result = !(CollectionUtils.containsAny(currentLine, intersection));
        return result;
    }
}
