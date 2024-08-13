package dev.khtml.hackathon.hospital;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.khtml.hackathon.controller.dto.request.HospitalGetRequest;
import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import dev.khtml.hackathon.prompt.JsonMapper;
import dev.khtml.hackathon.prompt.PromptService;
import dev.khtml.hackathon.support.util.LatLonCalculator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitalService {
	private final PromptService promptService;
	private final HospitalRepository hospitalRepository;
	private final MedicalDepartmentRepository medicalDepartmentRepository;

	public List<ReasonResponse> getAppropriateHospitals(HospitalGetRequest request) {
		final double currentLatitude = request.latitude();
		final double currentLongitude = request.longitude();

		// 프롬프트로 reason 리스트 받아오면서, 진료과목도 함께 받아오기
		String result = promptService.getResult(request.prompt());
		List<ReasonResponse> parsedReason = JsonMapper.parseJsonAndMapToReasonResponse(result);

		try {
			// 해당 진료과목을 가진 병원 중, 가장 가까운 병원 찾아서 반환
			return getFilledReasones(parsedReason, currentLatitude, currentLongitude);
		} catch (Exception e) {
			e.printStackTrace();
			return parsedReason;
		}
	}

	@Transactional(readOnly = true)
	public List<ReasonResponse> getFilledReasones(List<ReasonResponse> parsedReason, double currentLatitude,
		double currentLongitude) {
		List<ReasonResponse> filledReasones = parsedReason.stream().map(reason -> {
			MedicalDepartmentEnum departmentEnum = MedicalDepartmentEnum.valueOf(reason.detail().getSubject());

			// 같은 진료과목의 서로다른 병원들
			double minDistance = Double.MAX_VALUE;
			Hospital nearestHospital = null;
			List<MedicalDepartment> byDepartment = medicalDepartmentRepository.findByDepartment(departmentEnum);
			for (MedicalDepartment dep : byDepartment) {
				Hospital hospital = dep.getHospital();
				double distance = LatLonCalculator.getDistance(
					currentLatitude,
					currentLongitude,
					hospital.getLatitude(),
					hospital.getLongitude());
				if (distance <= minDistance) {
					minDistance = distance;
					nearestHospital = hospital;
				}
			}
			// 그 중, 가장 가까운 병원
			if (nearestHospital != null) {
				reason.updateHospital(nearestHospital, minDistance);
			}
			return reason;
		}).toList();
		return filledReasones;
	}
}
