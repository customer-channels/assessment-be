package com.ista.isp.assessment.todo.dto;

import lombok.Data;

@Data
public class ErrorDTO {
	
	String id;
	String message;
	
	public ErrorDTO() {
		super();
	}
	
	public ErrorDTO(String id, String message) {
		this.id = id;
		this.message = message;
	}
	
}
