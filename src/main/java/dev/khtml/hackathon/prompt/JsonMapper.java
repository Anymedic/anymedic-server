package dev.khtml.hackathon.prompt;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper {
	public static List<ReasonResponse> parseJsonAndMapToReasonResponse(String rawJson) {
		/// ```json ```	제거
		String cleanedJson = rawJson.replaceAll("(?m)^```json|```$|^---|---$", "").trim();

		List<ReasonResponse> cases = new ArrayList<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON의 "cases" 배열을 List<ReasonResponse>로 변환
			cases = objectMapper.readValue(
				objectMapper.readTree(cleanedJson).get("cases").toString(),
				new TypeReference<List<ReasonResponse>>() {
				}
			);
			// 결과 출력
			printResult(cases);
		} catch (Exception e) {
			log.error(rawJson);
			e.printStackTrace();
		}
		return cases;
	}

	private static void printResult(List<ReasonResponse> cases) {
		for (ReasonResponse reasonResponse : cases) {
			log.info("Reason: " + reasonResponse.reason());
			log.info("Description: " + reasonResponse.detail().getDescription());
			log.info("Manage: " + reasonResponse.detail().getManage());
			log.info("Is Emergency: " + reasonResponse.detail().isEmergency());
			log.info("Subject: " + reasonResponse.detail().getSubject());
		}
	}
}
