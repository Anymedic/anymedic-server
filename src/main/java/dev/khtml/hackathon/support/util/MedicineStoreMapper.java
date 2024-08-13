package dev.khtml.hackathon.support.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.khtml.hackathon.dto.MedicineStoreDTO;
import dev.khtml.hackathon.entity.MedicineStore;

@Component
public class MedicineStoreMapper {

	private LatLonCalculator latLonCalculator;

	public MedicineStoreMapper(LatLonCalculator latLonCalculator) {
		this.latLonCalculator = latLonCalculator;
	}

	public MedicineStoreDTO toInfo(MedicineStore medicineStore) {
		return MedicineStoreDTO.builder()
			.name(medicineStore.getName())
			.roadAddress(medicineStore.getRoadAddress())
			.latitude(medicineStore.getLatitude() != null ? medicineStore.getLatitude().doubleValue() : null)
			.longitude(medicineStore.getLongitude() != null ? medicineStore.getLongitude().doubleValue() : null)
			.phoneNumber(medicineStore.getPhoneNumber())
			.build();
	}

	public List<MedicineStoreDTO> toInfoList(List<MedicineStore> medicineStores) {
		return medicineStores.stream()
			.map(this::toInfo)
			.collect(Collectors.toList());
	}

	public MedicineStoreDTO.MedicineStoreResponse toNearMedicineStoreResponse(double latitude, double longitude,
		MedicineStoreDTO medicineStore) {
		return MedicineStoreDTO.MedicineStoreResponse.builder()
			.name(medicineStore.getName())
			.roadAddress(medicineStore.getRoadAddress())
			.latitude(medicineStore.getLatitude())
			.longitude(medicineStore.getLongitude())
			.distance(latLonCalculator.getDistance(
				latitude,
				longitude,
				medicineStore.getLatitude(),
				medicineStore.getLongitude()))
			.build();
	}

	public List<MedicineStoreDTO.MedicineStoreResponse> toNearMedicineStoreResponseList(double latitude,
		double longitude, List<MedicineStoreDTO> medicineStores) {
		List<MedicineStoreDTO.MedicineStoreResponse> responses = medicineStores.stream()
			.map(store -> toNearMedicineStoreResponse(latitude, longitude, store))
			.collect(Collectors.toList());

		return responses.stream()
			.sorted((store1, store2) -> Double.compare(store1.getDistance(), store2.getDistance()))
			.collect(Collectors.toList());

	}
}
