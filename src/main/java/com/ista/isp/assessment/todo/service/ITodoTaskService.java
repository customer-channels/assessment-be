package com.ista.isp.assessment.todo.service;

import java.util.List;

import com.ista.isp.assessment.todo.dto.TodoTaskDTO;

public interface ITodoTaskService {
	
	public List<TodoTaskDTO> findByTaskDone(Boolean isDone);

	public TodoTaskDTO findById(Long id);

	public List<TodoTaskDTO> findAll();

	public TodoTaskDTO createTodoTask(TodoTaskDTO taskDto);
	
	public TodoTaskDTO updateTodoTask(Long id, TodoTaskDTO taskUpdated);
	
	public void deleteTodoTask(Long id);
	
	public void deleteAll();
}
