package dev.khtml.hackathon.hospital;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import dev.khtml.hackathon.prompt.JsonPromptMapper;
import dev.khtml.hackathon.prompt.PromptService;
import dev.khtml.hackathon.support.util.LatLonCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class HospitalService {
	private final PromptService promptService;
	private final MedicalDepartmentRepository medicalDepartmentRepository;

	public List<ReasonResponse> getReasonsWithoutHospital(String prompt) {
		long startTime = System.currentTimeMillis(); // Start time logging
		// 프롬프트로 reason 리스트 받아오면서, 진료과목도 함께 받아옴
		String result = promptService.getResult(prompt);
		List<ReasonResponse> reasonResponses = JsonPromptMapper.parseJsonAndMapToReasonResponse(result);
		long endTime = System.currentTimeMillis(); // Start time logging
		log.info(">>>>> getAppropriateHospitals executed in {} ms", (endTime - startTime));
		return reasonResponses;
	}

	@Transactional
	public List<ReasonResponse> getCompleteReasons(List<ReasonResponse> parsedReason, double currentLatitude,
		double currentLongitude) {
		try {
			log.info(">>>>> Find a nearest hospital. [x,y]: [{},{}]", currentLatitude, currentLongitude);
			List<ReasonResponse> filledReasons = parsedReason.stream().map(reason -> {
				MedicalDepartmentEnum departmentEnum = MedicalDepartmentEnum.valueOf(reason.detail().getSubject());
				log.info(">>>>> department: {}", departmentEnum);

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
						log.info(">>>>> update nearest hospital!!!!: {}", hospital.getName());
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
			return filledReasons;
		} catch (Exception e) {
			e.printStackTrace();
			return parsedReason;
		}
	}
}
