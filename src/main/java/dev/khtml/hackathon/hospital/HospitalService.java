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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class HospitalService {
	private final PromptService promptService;
	private final HospitalRepository hospitalRepository;
	private final MedicalDepartmentRepository medicalDepartmentRepository;

	public List<ReasonResponse> getAppropriateHospitals(HospitalGetRequest request) {
		final double currentLatitude = request.latitude();
		final double currentLongitude = request.longitude();

		long startTime = System.currentTimeMillis(); // Start time logging
		// 프롬프트로 reason 리스트 받아오면서, 진료과목도 함께 받아오기
		String result = promptService.getResult(request.prompt());
		List<ReasonResponse> parsedReason = JsonMapper.parseJsonAndMapToReasonResponse(result);
		long endTime = System.currentTimeMillis(); // Start time logging
		log.info(">>>>> getAppropriateHospitals executed in {} ms", (endTime - startTime));


		try {
			// 해당 진료과목을 가진 병원 중, 가장 가까운 병원 찾아서 반환
			return getFilledReasons(parsedReason, currentLatitude, currentLongitude);
		} catch (Exception e) {
			e.printStackTrace();
			return parsedReason;
		}
	}

	@Transactional(readOnly = true)
	public List<ReasonResponse> getFilledReasons(List<ReasonResponse> parsedReason, double currentLatitude,
		double currentLongitude) {
		log.info(">>>>> Find a nearest hospital. [x,y]: [{},{}]", currentLatitude, currentLongitude);
		List<ReasonResponse> filledReasons = parsedReason.stream().map(reason -> {
			MedicalDepartmentEnum departmentEnum = MedicalDepartmentEnum.valueOf(reason.detail().getSubject());
			log.info(">>>>> department: {}", departmentEnum);

			// 같은 진료과목의 서로다른 병원들
			double minDistance = Double.MAX_VALUE;
			Hospital nearestHospital = null;
			long startTime2 = System.currentTimeMillis();
			List<MedicalDepartment> byDepartment = medicalDepartmentRepository.findByDepartment(departmentEnum);
			long endTime2 = System.currentTimeMillis();
			log.info(">>>>> findByDepartment executed in {} ms", (endTime2 - startTime2));

			long startTime3 = System.currentTimeMillis();
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
			long endTime3 = System.currentTimeMillis();
			log.info(">>>>> calculate executed in {} ms", (endTime3 - startTime3));

			// 그 중, 가장 가까운 병원
			if (nearestHospital != null) {
				reason.updateHospital(nearestHospital, minDistance);
			}
			return reason;
		}).toList();
		return filledReasons;
	}
}
