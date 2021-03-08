package com.ista.isp.assessment.todo.exception;

import org.springframework.http.HttpStatus;

import com.ista.isp.assessment.todo.dto.ErrorDTO;

public class TaskException extends RuntimeException {

	private static final long serialVersionUID = 2910940138579627176L;
	private ErrorDTO error;
	private HttpStatus httpStatus;
	
	public TaskException(ErrorDTO error, HttpStatus httpStatus) {
		super();
		this.error = error;
		this.httpStatus = httpStatus;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
