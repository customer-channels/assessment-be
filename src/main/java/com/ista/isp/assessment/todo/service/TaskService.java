package com.ista.isp.assessment.todo.service;

import java.util.List;

import com.ista.isp.assessment.todo.model.Task;

public interface TaskService {
	
	List<Task> findAllTasksByProperties(List<String> filter);
	
	Task getTask(Long taskId);
	
	Task createTask(Task task);
	
	Task updateTask(Long taskId, Task task);
	
	void deleteTask(Long taskId);
	
}
