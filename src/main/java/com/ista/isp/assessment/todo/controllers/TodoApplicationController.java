package com.ista.isp.assessment.todo.controllers;

import com.ista.isp.assessment.todo.dto.TaskRqDto;
import com.ista.isp.assessment.todo.dto.TaskRsDto;
import com.ista.isp.assessment.todo.services.TodoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("todo-tasks")
@Validated
@Slf4j
public class TodoApplicationController {

	@Autowired
	TodoTaskService todoTaskService;

	@GetMapping
	public List<TaskRsDto> getAllTodoList() {
		log.info("Listing all the TODO tasks ...");
		return todoTaskService.getTodoTaskList();
	}

	@GetMapping("/{id}")
	public TaskRsDto getTodoTask(@PathVariable int id) throws Exception  {
		log.info("Getting TODO task with id: {} ...", id);
		return todoTaskService.getTodoTask(id);
	}

	@PostMapping
	public TaskRsDto createTodoTask(@RequestBody @Valid TaskRqDto todoTask) throws Exception {
		log.info("Creating a new TODO task ...");
		return todoTaskService.createTodoTask(todoTask);
	}

	@PutMapping("/{id}")
	public TaskRsDto updateTodoTask(@RequestBody @Valid TaskRqDto todoTask, @PathVariable int id) throws Exception {
		log.info("Updating TODO task with id: {} ...", id);
		return todoTaskService.updateTodoTask(id, todoTask);
	}

}
