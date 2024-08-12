package dev.khtml.hackathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.khtml.hackathon.support.response.ApiResponse;

@RestController
public class TestController {

	@GetMapping
	public ApiResponse<TestResponse> test() {
		// logic
		return ApiResponse.success(new TestResponse("응답 성공 예시. '/' 외의 경로로 요청하면 오류 응답 예시를 확인할 수 있습니다."));
	}

	public record TestResponse(
		String something
	) {
	}
}
