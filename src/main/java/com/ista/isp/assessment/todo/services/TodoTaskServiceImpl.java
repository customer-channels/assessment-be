package com.ista.isp.assessment.todo.services;

import com.ista.isp.assessment.todo.dto.TaskRqDto;
import com.ista.isp.assessment.todo.dto.TaskRsDto;
import com.ista.isp.assessment.todo.entities.TodoTask;
import com.ista.isp.assessment.todo.exceptions.TodoTaskNotFoundException;
import com.ista.isp.assessment.todo.repositories.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {

	@Autowired
	private TodoTaskRepository todoTaskRepository;

	public List<TaskRsDto> getTodoTaskList() {
		List<TodoTask> todoTaskEntities = todoTaskRepository.findAll();

		return todoTaskEntities.stream().map(todoTaskEntity ->
						TaskRsDto.builder()
								.id(todoTaskEntity.getId())
								.taskDescription(todoTaskEntity.getDescription())
								.checked(todoTaskEntity.isChecked()).build())
				.collect(Collectors.toList());
	}

	public TaskRsDto getTodoTask(int id) throws TodoTaskNotFoundException {
		return todoTaskRepository.findById(id)
				.map(todoTask -> TaskRsDto.builder()
						.id(todoTask.getId())
						.taskDescription(todoTask.getDescription())
						.checked(todoTask.isChecked()).build())
				.orElseThrow(() -> new TodoTaskNotFoundException(id));
	}

	public TaskRsDto createTodoTask(TaskRqDto todoTask) {
		TodoTask todoTaskEntity = new TodoTask();
		todoTaskEntity.setDescription(todoTask.getTaskDescription());
		todoTaskEntity.setChecked(todoTask.getChecked());

		todoTaskRepository.save(todoTaskEntity);

		return TaskRsDto.builder()
			 .id(todoTaskEntity.getId())
			 .taskDescription(todoTaskEntity.getDescription())
			 .checked(todoTaskEntity.isChecked()).build();
	}

	public TaskRsDto updateTodoTask(int id, TaskRqDto todoTask) throws TodoTaskNotFoundException {
		TodoTask todoTaskEntity = todoTaskRepository.findById(id).orElseThrow(() -> new TodoTaskNotFoundException(id));

		todoTaskEntity.setDescription(todoTask.getTaskDescription());
		todoTaskEntity.setChecked(todoTask.getChecked());

		TodoTask newTodoTaskEntity = todoTaskRepository.save(todoTaskEntity);
		return TaskRsDto.builder().id(newTodoTaskEntity.getId()).taskDescription(newTodoTaskEntity.getDescription())
				.checked(newTodoTaskEntity.isChecked()).build();
	}


}
