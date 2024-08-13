package dev.khtml.hackathon.medicinestore;

import dev.khtml.hackathon.controller.dto.MedicineStoreDTO;
import dev.khtml.hackathon.support.util.LatLonCalculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface MedicineStoreRepository extends JpaRepository<MedicineStore, Long> {
    List<MedicineStore> findByLatitudeBetween(double minLat, double maxLat);

    @Service
    class MedicineStoreService {

        private final LatLonCalculator latLonCalculator;
        private final MedicineStoreRepository medicineStoreRepository;
        private MedicineStoreMapper medicineStoreMapper;

        public MedicineStoreService(
            LatLonCalculator latLonCalculator,
            MedicineStoreRepository medicineStoreRepository
        ) {
            this.latLonCalculator = latLonCalculator;
            this.medicineStoreRepository = medicineStoreRepository;
            this.medicineStoreMapper = new MedicineStoreMapper(latLonCalculator);
        }

        public List<MedicineStoreDTO.MedicineStoreResponse> getNearMedicineStores(Double latitude, Double longitude,
                                                                                  Integer radiusMeter) {

            double maxLatitude = latitude + LatLonCalculator.calculateLatDifference(radiusMeter);
            double minLatitude = latitude - LatLonCalculator.calculateLatDifference(radiusMeter);

            List<MedicineStore> medicineStoreList = medicineStoreRepository.findByLatitudeBetween(minLatitude, maxLatitude);

            List<MedicineStoreDTO> nearbyLocations = LatLonCalculator.filterLocationsWithinRadius(
                medicineStoreMapper.toInfoList(medicineStoreList), latitude, longitude, radiusMeter);

            return medicineStoreMapper.toNearMedicineStoreResponseList(latitude, longitude, nearbyLocations);
        }

    }
}
