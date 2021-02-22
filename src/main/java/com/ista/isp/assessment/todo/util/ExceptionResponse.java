package com.ista.isp.assessment.todo.util;

import java.sql.Timestamp;

public class ExceptionResponse {
	
	private Timestamp timestamp;
	private String message;
	private int responseCode;
	private String status;
	
	public ExceptionResponse() {
		
	}

	public ExceptionResponse(Timestamp timestamp, String message, int responseCode, String status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.responseCode = responseCode;
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}