package dev.khtml.hackathon.support.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.khtml.hackathon.dto.MedicineStoreDTO;

@Service
public class LatLonCalculator {
	private static final double DEGREE_METER = 111320.0;
	private static final double EARTH_RADIUS = 6371000;

	public static double calculateLatDifference(double radiusMeter) {
		double latDifference = radiusMeter / DEGREE_METER;
		return latDifference;
	}

	public static List<MedicineStoreDTO> filterLocationsWithinRadius(List<MedicineStoreDTO> locations, double latitude,
		double longitude, double radius) {

		List<MedicineStoreDTO> finalFilteredLocations = new ArrayList<>();
		for (MedicineStoreDTO location : locations) {
			double lonDifference = calculateLonDifference(latitude, radius);

			if (Math.abs(location.getLongitude() - longitude) <= lonDifference) {
				finalFilteredLocations.add(location);
			}
		}

		return finalFilteredLocations;
	}

	private static double calculateLonDifference(double latitude, double radius) {
		return radius / (DEGREE_METER * Math.cos(Math.toRadians(latitude)));
	}

	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double deltaLat = Math.toRadians(lat2 - lat1);
		double deltaLon = Math.toRadians(lon2 - lon1);

		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
			Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

		double centralAngle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		// m 로 반환
		return EARTH_RADIUS * centralAngle;
	}

}
