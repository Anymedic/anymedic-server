package dev.khtml.hackathon.prompt;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.khtml.hackathon.controller.dto.response.ReasonResponse;
import dev.khtml.hackathon.support.error.ErrorType;
import dev.khtml.hackathon.support.error.PromptException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonPromptMapper {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static List<ReasonResponse> parseJsonAndMapToReasonResponse(String rawJson) {
		List<ReasonResponse> cases = new ArrayList<>();
		// ```json ```제거
		String cleanedJson = rawJson.replaceAll("(?m)^```json|```$|^---|---$", "").trim();
		Boolean isException = parseJsonToObject(cleanedJson, "exception", new TypeReference<Boolean>() {
		});
		if (isException) {
			String exceptionMessage = parseJsonToObject(cleanedJson, "exceptionMessage", new TypeReference<String>() {
			});
			log.warn(exceptionMessage);
			throw new PromptException(ErrorType.BAD_REQUEST, exceptionMessage);
		} else {
			cases = parseJsonToObject(cleanedJson, "cases",
				new TypeReference<List<ReasonResponse>>() {
				});
			log.info(cases.toString());
			return cases;
		}
	}

	public static <T> T parseJsonToObject(String json, String fieldName, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(objectMapper.readTree(json).get(fieldName).toString(), typeReference);
		} catch (Exception e) {
			log.error(">>>>>> Json Parse Error: {}", json);
			throw new RuntimeException(e);
		}
	}
}
