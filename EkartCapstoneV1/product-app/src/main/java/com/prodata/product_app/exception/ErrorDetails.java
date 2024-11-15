package com.prodata.product_app.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String path;
	private String details;

	public ErrorDetails(LocalDateTime timeStamp, String message, String path) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
	}

	public ErrorDetails(LocalDateTime timeStamp, String message, String path, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
		this.details = details;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
