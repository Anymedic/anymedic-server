package dev.khtml.hackathon.dto;

import dev.khtml.hackathon.controller.MedicineStoreController;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MedicineStoreDTO {

    private String name;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private String roadAddress;


    @Getter
    @Setter
    @Builder
    public static class MedicineStoreResponse {
        private String name;
        private String roadAddress;
        private double latitude;
        private double longitude;
        private double distance;
    }

}
