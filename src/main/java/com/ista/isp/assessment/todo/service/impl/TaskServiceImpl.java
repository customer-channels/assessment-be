package com.ista.isp.assessment.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.isp.assessment.todo.dto.ErrorDTO;
import com.ista.isp.assessment.todo.dto.SearchCriteria;
import com.ista.isp.assessment.todo.exception.TaskException;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.repository.TaskRepository;
import com.ista.isp.assessment.todo.repository.TaskSpecification;
import com.ista.isp.assessment.todo.service.TaskService;
import com.ista.isp.assessment.todo.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Task> findAllTasksByProperties(List<String> filter) {

		List<Task> tasks = new ArrayList<>();

		if (filter == null || filter.isEmpty()) {
			tasks = taskRepository.findAllByOrderByCheckedDesc();

		} else {
			TaskSpecification specification = getSpecification(filter);

			tasks = taskRepository.findAll(specification);
		}

		return tasks;
	}

	private TaskSpecification getSpecification(List<String> filters) {

		List<SearchCriteria> criterias = new ArrayList<>();

		for (String filter : filters)
			criterias.add(new SearchCriteria(filter));

		TaskSpecification taskSpecification = new TaskSpecification(criterias);

		return taskSpecification;
	}

	@Override
	@Transactional(readOnly = true)
	public Task getTask(Long taskId) {

		Task task = taskRepository.findById(taskId).orElse(null);

		if (task != null) {
			log.info(Constants.LOG_FOUND, taskId);
			return task;

		} else {
			log.error(Constants.LOG_NOT_FOUND, taskId);
			throw new TaskException(
					new ErrorDTO(Constants.ERROR_ID_TASK_NOT_FOUND, Constants.ERROR_MESSAGE_TASK_NOT_FOUND + taskId),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@Transactional
	public Task createTask(Task task) {
		
		validateEmptyBody(task);

		Task savedTask = taskRepository.save(task);

		log.info(Constants.LOG_CREATED, savedTask.getId());

		return savedTask;
	}

	@Override
	@Transactional
	public Task updateTask(Long taskId, Task task) {

		validateEmptyBody(task);
		validateMismatchTaskIds(taskId, task);
		validateTaskExists(taskId);

		Task updatedTask = taskRepository.save(task);

		log.info(Constants.LOG_UPDATED, updatedTask.getId());

		return updatedTask;

	}

	@Override
	@Transactional
	public void deleteTask(Long taskId) {

		validateTaskExists(taskId);

		taskRepository.deleteById(taskId);

		log.info(Constants.LOG_DELETED, taskId);
	}
	
	private void validateMismatchTaskIds(Long taskId, Task task) {
		if (!taskId.equals(task.getId())) {
			log.error(Constants.LOG_ERROR, Constants.ERROR_MESSAGE_ID_TASK_MISMATCH);
			throw new TaskException(
					new ErrorDTO(Constants.ERROR_ID_TASK_MISMATCH, Constants.ERROR_MESSAGE_ID_TASK_MISMATCH),
					HttpStatus.BAD_REQUEST);
		}
	}

	private void validateEmptyBody(Task task) {
		if (task.getText() == null && task.getChecked() == null) {
			log.error(Constants.LOG_ERROR, Constants.ERROR_ID_EMPTY_BODY);
			throw new TaskException(new ErrorDTO(Constants.ERROR_ID_EMPTY_BODY, Constants.ERROR_MESSAGE_EMPTY_BODY),
					HttpStatus.BAD_REQUEST);
		}
	}

	private void validateTaskExists(Long taskId) {
		if (!taskRepository.existsById(taskId)) {
			log.error(Constants.LOG_NOT_FOUND, taskId);
			throw new TaskException(
					new ErrorDTO(Constants.ERROR_ID_TASK_NOT_FOUND, Constants.ERROR_MESSAGE_TASK_NOT_FOUND + taskId),
					HttpStatus.NOT_FOUND);
		}
	}

}
