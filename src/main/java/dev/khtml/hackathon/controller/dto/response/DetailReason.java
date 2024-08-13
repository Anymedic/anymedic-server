package dev.khtml.hackathon.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public final class DetailReason {
	private final String description;
	private final String manage;
	private final boolean isEmergency;
	private final String subject;
	private Hospital hospital;

	public DetailReason(
		@JsonProperty("description") String description,
		@JsonProperty("manage") String manage,
		@JsonProperty("isEmergency") boolean isEmergency,
		@JsonProperty("subject") String subject
	) {
		this.description = description;
		this.manage = manage;
		this.isEmergency = isEmergency;
		this.subject = subject;
	}

}
