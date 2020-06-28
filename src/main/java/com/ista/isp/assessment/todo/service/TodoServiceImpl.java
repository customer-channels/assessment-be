package com.ista.isp.assessment.todo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.isp.assessment.todo.entity.Todo;
import com.ista.isp.assessment.todo.exception.TodoNotFoundException;
import com.ista.isp.assessment.todo.repository.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Optional<Todo> getTodoById(Long todoId) {
		return todoRepository.findById(todoId);
	}

	@Override
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo updateTodo(Long todoId, Todo newTodo) throws TodoNotFoundException {
		Todo todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new TodoNotFoundException("Todo not found by id: " + todoId));
		todo.setName(newTodo.getName());
		todo.setSelected(newTodo.isSelected());
		return todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Long todoId) throws TodoNotFoundException {
		Todo todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new TodoNotFoundException("Todo not found by id: " + todoId));
		todoRepository.delete(todo);
	}
}
