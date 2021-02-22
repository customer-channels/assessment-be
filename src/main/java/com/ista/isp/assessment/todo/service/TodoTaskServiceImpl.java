package com.ista.isp.assessment.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ista.isp.assessment.todo.dto.TodoTaskDTO;
import com.ista.isp.assessment.todo.entity.TodoTaskEntity;
import com.ista.isp.assessment.todo.exception.TaskNotFoundException;
import com.ista.isp.assessment.todo.repository.ITodoTaskRepository;

@Service
public class TodoTaskServiceImpl implements ITodoTaskService {

	@Autowired
	private ITodoTaskRepository taskRepository;

	@Bean
	private ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public TodoTaskDTO findById(Long id) {
		return convertToDto(getEntityByIdOrThrowNotFoundException(id));
	}

	@Override
	public List<TodoTaskDTO> findAll() {
		List<TodoTaskEntity> taskList = this.taskRepository.findAll();
		return taskList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public TodoTaskDTO createTodoTask(TodoTaskDTO taskDto) {
		return convertToDto(this.taskRepository.save(convertToEntity(taskDto)));
	}

	@Override
	public TodoTaskDTO updateTodoTask(Long id, TodoTaskDTO taskUpdated) {
		TodoTaskEntity task = getEntityByIdOrThrowNotFoundException(id);
		task.setDescription(taskUpdated.getDescription());
		task.setDone(taskUpdated.getDone());
		return convertToDto(this.taskRepository.save(task));
	}

	@Override
	public void deleteTodoTask(Long id) {
		this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
		this.taskRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		this.taskRepository.deleteAll();
	}

	@Override
	public List<TodoTaskDTO> findByTaskDone(Boolean isDone) {
		List<TodoTaskEntity> taskList = new ArrayList<TodoTaskEntity>();
		Example<TodoTaskEntity> taskDone = Example.of(new TodoTaskEntity(isDone));
		this.taskRepository.findAll(taskDone).forEach(taskList::add);
		return taskList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private TodoTaskEntity convertToEntity(TodoTaskDTO taskDto) {
		return modelMapper().map(taskDto, TodoTaskEntity.class);
	}

	private TodoTaskDTO convertToDto(TodoTaskEntity taskEntity) {
		return modelMapper().map(taskEntity, TodoTaskDTO.class);
	}

	private TodoTaskEntity getEntityByIdOrThrowNotFoundException(Long id) {
		return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}
}
