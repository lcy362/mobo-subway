/**
 * @(#)GeoUtils.java, 5月 19, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mobo.mobosubway.util;

/**
 * @author licy03
 */
public class GeoUtils {

    private final static double EARTH_RADIUS = 6378.137;

    /**
     * calculate distance with lonitude and latitude
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return meter
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double a = Math.toRadians(lat2-lat1);
        double b = Math.toRadians(lng2-lng1);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10;
        return s;
    }
}
