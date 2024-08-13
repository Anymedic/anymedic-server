package dev.khtml.hackathon.controller.dto.response;

import dev.khtml.hackathon.hospital.Hospital;

public record ReasonResponse(
	String reason,
	DetailReason detail
) {
	public void updateHospital(Hospital hospital, double distance) {
		detail.updateHospital(new HospitalResponse(
			hospital.getName(),
			hospital.getRoadAddress(),
			hospital.getLatitude(),
			hospital.getLongitude(),
			distance,
			detail().getSubject()
		));
	}
}
