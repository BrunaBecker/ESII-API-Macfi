package com.macfi.model.utils;

public class GeoLocation {

    static final double RADIUS_MACFI = 0.1;
    static final double EARTH_RADIUS = 6371.00;


    public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        // Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

        return EARTH_RADIUS * c;
    }

    public static boolean inRadiusMacfi(double lat1, double lon1, double lat2, double lon2) {
        double d = GeoLocation.calDistance(lat1, lon1, lat2, lon2);
        return d <= RADIUS_MACFI;
    }

}
