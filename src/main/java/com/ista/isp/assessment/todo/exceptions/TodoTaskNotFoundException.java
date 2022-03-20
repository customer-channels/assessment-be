package com.ista.isp.assessment.todo.exceptions;

public class TodoTaskNotFoundException extends RuntimeException {

	public TodoTaskNotFoundException(int id){
		super(String.format("Task with id %d was not found", id));
	}
}
