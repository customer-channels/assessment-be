package com.ista.isp.assessment.todo.exception;

public class TaskNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7065270926499716543L;
	private Long taskId;
	
	public TaskNotFoundException(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	public String getMessage() {
		return String.format("Todo Task with ID '%s' NOT FOUND", this.taskId);
	}
}
