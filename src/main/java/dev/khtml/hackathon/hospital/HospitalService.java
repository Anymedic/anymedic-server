package dev.khtml.hackathon.hospital;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.khtml.hackathon.controller.dto.request.HospitalGetRequest;
import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import dev.khtml.hackathon.prompt.JsonMapper;
import dev.khtml.hackathon.prompt.PromptService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitalService {
	private final PromptService promptService;
	private final HospitalRepository hospitalRepository;
	private final MedicalDepartmentRepository medicalDepartmentRepository;

	public List<ReasonResponse> getAppropriateHospitals(HospitalGetRequest request) {
		final double latitude = request.latitude();
		final double longitude = request.longitude();

		// 프롬프트로 reason 리스트 받아오면서, 진료과목도 함께 받아오기
		String result = promptService.getResult(request.prompt());
		List<ReasonResponse> reasonResponses = JsonMapper.parseJsonAndMapToReasonResponse(result);

		// 해당 진료과목을 가진 병원 중, 가장 가까운 병원 찾기

		// 병원 정보도 담아서 함께 전달
		return reasonResponses;
	}
}
