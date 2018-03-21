package com.solo.subway;

import com.solo.subway.util.Station;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

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
}
