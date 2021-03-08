package com.ista.isp.assessment.todo.dto;

import lombok.Data;

@Data
public class SearchCriteria {

	private String key;
	private String operation;
	private Object value;
	
	public SearchCriteria() {
		super();
	}
	
	public SearchCriteria(String key, String operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public SearchCriteria(String filter) {
		super();
		String[] params = filter.split(":");
		this.key = params[0];
		this.operation = ":";
		this.value = params[1];
	}

}
