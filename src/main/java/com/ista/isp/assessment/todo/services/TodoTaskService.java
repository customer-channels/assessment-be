package com.ista.isp.assessment.todo.services;

import com.ista.isp.assessment.todo.dto.TaskRqDto;
import com.ista.isp.assessment.todo.dto.TaskRsDto;
import com.ista.isp.assessment.todo.exceptions.TodoTaskNotFoundException;

import java.util.List;

public interface TodoTaskService {

	/**
	 * Retrieves and returns a list of all the TODO tasks.
	 *
	 * @return List of TaskDto.
	 */
	List<TaskRsDto> getTodoTaskList();

	/**
	 * Retrieves and returns a particular TODO task.
	 *
	 * @param id 	the internal id of the TODO task.
	 * @return 		the TaskDto object.
	 * @throws 		TodoTaskNotFoundException if the id was not found.
	 */
	TaskRsDto getTodoTask(int id) throws TodoTaskNotFoundException;

	/**
	 * Creates a TODO task and returns the new resource.
	 *
	 * @param todoTask 	the DTO with data from the new TODO task.
	 * @return 			the new TaskDto object with the id.
	 */
	TaskRsDto createTodoTask (TaskRqDto todoTask);

	/**
	 * Updates a TODO task and returns the updated resource.
	 *
	 * @param id		the
	 * @param todoTask 	the DTO with data from the updated TODO task.
	 * @return 			the updated TaskDto object.
	 * @throws 			TodoTaskNotFoundException if the id was not found.
	 */
	TaskRsDto updateTodoTask(int id, TaskRqDto todoTask) throws TodoTaskNotFoundException;

}
