package com.ista.isp.assessment.todo.utils;

public class Constants {

	public static final String LOG_FOUND = "Found task: {}";
	public static final String LOG_NOT_FOUND = "Not found task: {}";
	public static final String LOG_CREATED = "Created task: {}";
	public static final String LOG_UPDATED = "Updated task: {}";
	public static final String LOG_DELETED = "Deleted task: {}";
	public static final String LOG_ERROR = "Error: {}";
	
	
	// Errors
	public static final String ERROR_ID_TASK_NOT_FOUND = "TASK_NOT_FOUND";
	public static final String ERROR_MESSAGE_TASK_NOT_FOUND = "Task not found: ";
	
	public static final String ERROR_ID_TASK_MISMATCH = "ID_TASK_MISMATCH";
	public static final String ERROR_MESSAGE_ID_TASK_MISMATCH = "Mismatch tasks Ids. ";
	
	public static final String ERROR_ID_EMPTY_BODY = "EMPTY_BODY";
	public static final String ERROR_MESSAGE_EMPTY_BODY = "Body must not be empty or null.";
}
