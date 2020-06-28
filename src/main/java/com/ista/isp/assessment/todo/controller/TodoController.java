package com.ista.isp.assessment.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.entity.Todo;
import com.ista.isp.assessment.todo.exception.TodoNotFoundException;
import com.ista.isp.assessment.todo.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return todoService.getAllTodos();
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable(value = "id") Long todoId) {
		Todo todo = todoService.getTodoById(todoId).get();
		return ResponseEntity.ok().body(todo);
	}

	@PostMapping("/todos")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		return todoService.createTodo(todo);
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable(value = "id") Long todoId, @Valid @RequestBody Todo newTodo)
			throws TodoNotFoundException {
		return ResponseEntity.ok(todoService.updateTodo(todoId, newTodo));
	}

	@DeleteMapping("/todos/{id}")
	public Map<String, Boolean> deleteTodo(@PathVariable(value = "id") Long todoId) throws TodoNotFoundException {

		todoService.deleteTodo(todoId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
