package com.ista.isp.assessment.todo.service;

import java.util.List;
import java.util.Optional;

import com.ista.isp.assessment.todo.entity.Todo;
import com.ista.isp.assessment.todo.exception.TodoNotFoundException;

public interface TodoService {

	List<Todo> getAllTodos();

	Optional<Todo> getTodoById(Long todoId);

	Todo createTodo(Todo todo);

	Todo updateTodo(Long todoId, Todo todo) throws TodoNotFoundException;

	void deleteTodo(Long todoId) throws TodoNotFoundException ;
}
