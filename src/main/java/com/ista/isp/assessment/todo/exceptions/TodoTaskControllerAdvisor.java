package com.ista.isp.assessment.todo.exceptions;

import com.ista.isp.assessment.todo.dto.ErrorDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TodoTaskControllerAdvisor extends ResponseEntityExceptionHandler {

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
	 *
	 * @param ex      MissingServletRequestParameterException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ErrorDto object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new ErrorDto(HttpStatus.BAD_REQUEST, error, ex));
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
	 *
	 * @param ex      HttpMediaTypeNotSupportedException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ErrorDto object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(new ErrorDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ErrorDto object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST);
		errorDto.setMessage("Validation error");
		errorDto.addValidationErrors(ex.getBindingResult().getFieldErrors());
		errorDto.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(errorDto);
	}

	/**
	 * Handles TodoTaskNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
	 *
	 * @param ex the TodoTaskNotFoundException
	 * @return the ErrorDto object
	 */
	@ExceptionHandler(TodoTaskNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(
			TodoTaskNotFoundException ex) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND);
		errorDto.setMessage(ex.getMessage());
		return buildResponseEntity(errorDto);
	}

	/**
	 * Handles EntityNotFoundException. The rest of javax.persistence.EntityNotFoundException errors.
	 *
	 * @param ex the EntityNotFoundException
	 * @return the ErrorDto object
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(
			EntityNotFoundException ex) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND);
		errorDto.setMessage(ex.getMessage());
		return buildResponseEntity(errorDto);
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ErrorDto object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ErrorDto(HttpStatus.BAD_REQUEST, error, ex));
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 *
	 * @param ex      HttpMessageNotWritableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ErrorDto object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Error writing JSON output";
		return buildResponseEntity(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	/**
	 * Handle Exception, handle generic MethodArgumentTypeMismatchException.class
	 *
	 * @param ex the MethodArgumentTypeMismatchException
	 * @return the ErrorDto object
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST);
		errorDto.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		errorDto.setDebugMessage(ex.getMessage());
		return buildResponseEntity(errorDto);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorDto errorDto) {
		return new ResponseEntity<>(errorDto, errorDto.getStatus());
	}

}
