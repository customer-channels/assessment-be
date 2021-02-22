package com.ista.isp.assessment.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.dto.TodoTaskDTO;
import com.ista.isp.assessment.todo.service.ITodoTaskService;

@RestController
@RequestMapping("/todotasks")
public class TodoTaskController {

	@Autowired
	private ITodoTaskService todoTaskService;

	@GetMapping
	public ResponseEntity<List<TodoTaskDTO>> getAllTasks() {
		return new ResponseEntity<>(todoTaskService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TodoTaskDTO> createTask(@RequestBody @Valid TodoTaskDTO taskDto) {
		return new ResponseEntity<>(todoTaskService.createTodoTask(taskDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TodoTaskDTO> findTaskById(@PathVariable("id") long id) {
		return new ResponseEntity<>(todoTaskService.findById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TodoTaskDTO> updateTask(@PathVariable("id") long id,
			@RequestBody @Valid TodoTaskDTO updatedTask) {
		return new ResponseEntity<>(todoTaskService.updateTodoTask(id, updatedTask), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllTasks() {
			todoTaskService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
		todoTaskService.deleteTodoTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/done")
	public ResponseEntity<List<TodoTaskDTO>> findByTaskDone() {
		return new ResponseEntity<>(todoTaskService.findByTaskDone(true), HttpStatus.OK);
	}
}
