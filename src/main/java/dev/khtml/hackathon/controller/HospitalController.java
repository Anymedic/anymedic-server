package dev.khtml.hackathon.controller;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.khtml.hackathon.controller.dto.request.HospitalGetRequest;
import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import dev.khtml.hackathon.hospital.HospitalService;
import dev.khtml.hackathon.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/medical")
public class HospitalController {
	private final HospitalService hospitalService;

	@PostMapping("/prompt")
	public ApiResponse<List<ReasonResponse>> getHospitals(@RequestBody HospitalGetRequest request) {
		List<ReasonResponse> response = hospitalService.getAppropriateHospitals(request);
		return ApiResponse.success(response);
	}

	@GetMapping("/search")
	public List<Document> search(@RequestParam String question) {
		return null;
	}
}
