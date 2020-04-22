package com.solo.subway.router;

import com.solo.subway.data.PathInfo;
import com.solo.subway.data.Station;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.solo.subway.util.StationsUtils.transferred;

@Service
@Slf4j
public class DijkstraRouter extends AbstractRouter{

    @Override
    public Map<String, PathInfo> pathToAll(String originName, Map<String, Station> stations) {
        Map<String, PathInfo> knownPath = initKnownPath(stations.values(), originName);
        Map<String, PathInfo> waitingPath = initWaitingPath(stations.values(), originName);
        String originId = getOriginId(stations.values(), originName);

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
                    log.info("set " + stations.get(nextId).getName() + " distance to " + nextLength);
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
                log.info(stations.get(now.getStationId()).getName() + " distance is " + now.getLength());
            }

        }

        //一次计算换乘信息
        for (PathInfo pathInfo : knownPath.values()) {
            if (!pathInfo.accessible()) {
                pathInfo.setTransferNum(-1);
                continue;
            }
            int transfer = 0;
            List<String> detail = pathInfo.getDetail();

            for (int i = 1; i < detail.size() - 1; i++) {
                Station pre = stations.get(detail.get(i - 1));
                Station next = stations.get(detail.get(i + 1));
                if (transferred(stations.get(detail.get(i)).getLines(), pre.getLines(), next.getLines())) {
                    transfer++;
                }
            }
            pathInfo.setTransferNum(transfer);
            log.info(originName + " to " + stations.get(pathInfo.getStationId()).getName()
             + " transfer " + transfer);
        }

        return knownPath;

    }
}
