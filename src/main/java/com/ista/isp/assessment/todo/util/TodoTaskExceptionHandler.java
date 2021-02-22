package com.ista.isp.assessment.todo.util;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ista.isp.assessment.todo.exception.TaskNotFoundException;

@RestControllerAdvice
public class TodoTaskExceptionHandler {

	private static final String EXCEPTION_TEXT = "[Exception Response] - Exception: ";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(MethodArgumentNotValidException e) {
		ExceptionResponse result = new ExceptionResponse(new Timestamp(System.currentTimeMillis()),
				EXCEPTION_TEXT + e.getMessage(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleTaskNotFoundException(TaskNotFoundException e) {
		ExceptionResponse result = new ExceptionResponse(new Timestamp(System.currentTimeMillis()),
				EXCEPTION_TEXT + e.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleInternalServerException(Exception e) {
		ExceptionResponse result = new ExceptionResponse(new Timestamp(System.currentTimeMillis()),
				EXCEPTION_TEXT + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}