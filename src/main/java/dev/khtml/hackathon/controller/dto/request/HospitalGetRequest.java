package dev.khtml.hackathon.controller.dto.request;

public record HospitalGetRequest(
	String prompt,
	double latitude,
	double longitude
) {
}
