package dev.khtml.hackathon.support.util;
import dev.khtml.hackathon.entity.MedicineStore;
import dev.khtml.hackathon.dto.MedicineStoreDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineStoreMapper {

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


    public MedicineStoreDTO.MedicineStoreResponse toResponse(MedicineStoreDTO medicineStore) {
        return MedicineStoreDTO.MedicineStoreResponse.builder()
                .name(medicineStore.getName())
                .roadAddress(medicineStore.getRoadAddress())
                .latitude(medicineStore.getLatitude())
                .longitude(medicineStore.getLongitude())
                .build();
    }

    public List<MedicineStoreDTO.MedicineStoreResponse> toResponseList(List<MedicineStoreDTO> medicineStores) {
        return medicineStores.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
