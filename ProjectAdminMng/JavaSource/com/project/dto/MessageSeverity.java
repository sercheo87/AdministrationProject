package com.project.dto;

public enum MessageSeverity {
	error("error"), info("info"), success("sucess"), warning("warning");

	@SuppressWarnings("unused")
	private String value;

	MessageSeverity(String value) {
		this.value = value;
	}
}
