package com.solo.subway;

import com.solo.subway.data.SubwayInfoParser;
import com.solo.subway.path.Dijkstra;
import com.solo.subway.util.PathInfo;
import com.solo.subway.data.Station;
import com.solo.subway.data.SubwayLine;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.IOException;
import java.util.Map;

public class SubwayTest {
    @Test
    public void stationTest() {
        Station s = new Station();
        s.getLines().add("s");
        System.out.println(s.getLines());
    }

    @Test
    public void dbTest() {
        DB store = DBMaker.fileDB("file.db").make();
        System.out.println(store.exists("test"));
        store.close();
    }

    @Test
    public void transferTest() throws IOException {
        SubwayInfoParser subway = SubwayInfoParser.getInstace();
        subway.parse();
        Map<String, Station> stations = subway.getStations();
        Map<String, SubwayLine> lineName = subway.getLineName();

        String destId = null;
        for (Station station : stations.values()) {
            if (station.getName().equals("西直门")) {
                destId = station.getId();
                break;
            }
        }

        Map<String, PathInfo> path = Dijkstra.pathToAll("平安里", stations);

        PathInfo pathInfo = path.get(destId);
        for (String node : pathInfo.getDetail()) {
            System.out.println(stations.get(node).getName());
        }
        System.out.println(pathInfo.getTransferNum());
    }
}
