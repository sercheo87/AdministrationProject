package com.project.dto;

public class Message {
	private int code;
	private String message;
	private MessageSeverity severity;
	private String title;

	public Message(int code, String message, MessageSeverity severity, String title) {
		super();
		this.code = code;
		this.message = message;
		this.severity = severity;
		this.title = title;
	}

	public Message(String message, MessageSeverity severity) {
		super();
		this.message = message;
		this.severity = severity;
	}

	public Message(String message, MessageSeverity severity, String title) {
		super();
		this.message = message;
		this.severity = severity;
		this.title = title;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public MessageSeverity getSeverity() {
		return this.severity;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSeverity(MessageSeverity severity) {
		this.severity = severity;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}