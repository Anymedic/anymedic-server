package dev.khtml.hackathon.support.util;

import dev.khtml.hackathon.dto.MedicineStoreDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LatLonCalculator {
    private static final double DEGREE_METER = 111320.0;

    public static double[] calculateLatLonDifference(double latitude, double radius) {
        double latDifference = radius / DEGREE_METER;
        double lonDifference = radius / (DEGREE_METER * Math.cos(Math.toRadians(latitude)));
        return new double[]{latDifference, lonDifference};
    }

    public static double[][] calculateLatLonRange(double latitude, double longitude, double radius) {
        double[] differences = calculateLatLonDifference(latitude, radius);
        double latMin = latitude - differences[0];
        double latMax = latitude + differences[0];
        double lonMin = longitude - differences[1];
        double lonMax = longitude + differences[1];
        return new double[][]{{latMin, latMax}, {lonMin, lonMax}};
    }

    public static double calculateMaxLatDifference(double latitude, double radiusMeter) {
        double latDifference = radiusMeter / DEGREE_METER;

        return latDifference;
    }

    public static List<MedicineStoreDTO> filterLocationsWithinRadius(List<MedicineStoreDTO> locations, double latitude, double longitude, double radius) {

        List<MedicineStoreDTO> finalFilteredLocations = new ArrayList<>();
        for (MedicineStoreDTO location : locations) {
            System.out.println("location : " + location.getName());
            System.out.println("location : " + location.getLongitude());
            double lonDifference = calculateLonDifference(latitude, radius);
            System.out.println("lonDifference : " + lonDifference);
            System.out.println("diff : " + Math.abs(location.getLongitude() - longitude));
            System.out.println(Math.abs(location.getLongitude() - longitude) <= lonDifference);

            if (Math.abs(location.getLongitude() - longitude) <= lonDifference) {
                finalFilteredLocations.add(location);
            }
        }

        return finalFilteredLocations;
    }

    private static double calculateLonDifference(double latitude, double radius) {
        return radius / (DEGREE_METER * Math.cos(Math.toRadians(latitude)));
    }

}
