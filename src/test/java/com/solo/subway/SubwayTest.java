package com.solo.subway;

import com.solo.subway.util.Station;
import org.junit.Test;

public class SubwayTest {
    @Test
    public void stationTest() {
        Station s = new Station();
        s.getLines().add("s");
        System.out.println(s.getLines());
    }
}
