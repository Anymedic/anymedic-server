package dev.khtml.hackathon.repository;

import dev.khtml.hackathon.entity.MedicineStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineStoreRepository extends JpaRepository<MedicineStore, Long> {
    List<MedicineStore> findByLatitudeBetween(double minLat, double maxLat);
}
