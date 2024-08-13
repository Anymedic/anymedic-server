package dev.khtml.hackathon.controller;

import dev.khtml.hackathon.controller.dto.MedicineStoreDTO;
import dev.khtml.hackathon.medicinestore.MedicineStoreRepository;
import dev.khtml.hackathon.support.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/medicine-stores")
public class MedicineStoreController {

    private final MedicineStoreRepository.MedicineStoreService medicineStoreService;

    public MedicineStoreController(MedicineStoreRepository.MedicineStoreService medicineStoreService) {
        this.medicineStoreService = medicineStoreService;
    }


    @GetMapping
    public ApiResponse<MedicineStoreResponse> getNearMedicineStores(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(required = false, defaultValue = "1000") Integer range
    ) {
        List<MedicineStoreDTO.MedicineStoreResponse> latLonMedicineList = medicineStoreService.getNearMedicineStores(latitude, longitude, range);

        return ApiResponse.success(new MedicineStoreResponse(latLonMedicineList));
    }



    public record MedicineStoreResponse(List<MedicineStoreDTO.MedicineStoreResponse> latLonMedicineList) {
    }
}
