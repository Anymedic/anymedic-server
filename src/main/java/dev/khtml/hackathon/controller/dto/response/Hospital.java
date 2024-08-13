package dev.khtml.hackathon.controller.dto.response;

public record Hospital(
	String name,       // 병원 이름
	String address,    // 병원 주소
	double x,   // 병원 위도 (좌표 X)
	double y,  // 병원 경도 (좌표 Y)
	int distance,      // 거리
	String subject     // 진료 과목
) {
}
