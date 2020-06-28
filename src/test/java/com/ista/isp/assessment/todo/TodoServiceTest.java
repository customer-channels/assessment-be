package com.ista.isp.assessment.todo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ista.isp.assessment.todo.entity.Todo;
import com.ista.isp.assessment.todo.exception.TodoNotFoundException;
import com.ista.isp.assessment.todo.repository.TodoRepository;
import com.ista.isp.assessment.todo.service.TodoService;
import com.ista.isp.assessment.todo.service.TodoServiceImpl;

@SpringBootTest
public class TodoServiceTest {

	@InjectMocks
	private TodoService todoService = new TodoServiceImpl();

	@Mock
	private TodoRepository todoRepository;

	private static final Long ID_1 = Long.valueOf(1);
	private static final Long ID_2 = Long.valueOf(2);
	private static final Optional<Todo> TODO = Optional.of(new Todo("todo", false));
	private static final Todo NEW_TODO = new Todo("todo", true);
	private static final List<Todo> TODO_LIST = new ArrayList<Todo>();

	@BeforeEach
	public void init() {
		Mockito.when(todoRepository.findById(ID_1)).thenReturn(TODO);
		Mockito.when(todoRepository.findById(ID_2)).thenReturn(Optional.empty());
		Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(NEW_TODO);
	}

	@Test()
	public void testUpdateTodo() throws TodoNotFoundException {
		Todo updatedTodo = todoService.updateTodo(ID_1, NEW_TODO);

		assertTrue(updatedTodo.isSelected());

		Mockito.verify(todoRepository, Mockito.times(1)).findById(ID_1);
		Mockito.verify(todoRepository, Mockito.times(1)).save(Mockito.any(Todo.class));
	}

	@Test()
	public void testUpdateTodoWhenNotExist() throws TodoNotFoundException {
		assertThrows(TodoNotFoundException.class, () -> todoService.updateTodo(ID_2, NEW_TODO));

		Mockito.verify(todoRepository, Mockito.times(1)).findById(ID_2);
		Mockito.verify(todoRepository, Mockito.never()).save(Mockito.any(Todo.class));
	}

	@Test
	public void testDeleteTodo() throws TodoNotFoundException {
		todoService.deleteTodo(ID_1);

		Mockito.verify(todoRepository, Mockito.times(1)).findById(ID_1);
		Mockito.verify(todoRepository, Mockito.times(1)).delete(TODO.get());
	}

	@Test()
	public void testDeleteTodoWhenNotExist() throws TodoNotFoundException {
		assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodo(ID_2));

		Mockito.verify(todoRepository, Mockito.times(1)).findById(ID_2);
		Mockito.verify(todoRepository, Mockito.never()).delete(TODO.get());
	}

	@Test
	public void testGetAllTodos() {
		TODO_LIST.add(NEW_TODO);
		Mockito.when(todoRepository.findAll()).thenReturn(TODO_LIST);

		List<Todo> todos = todoService.getAllTodos();

		assertTrue(1 == todos.size());

		Mockito.verify(todoRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void testGetTodoById() {
		Optional<Todo> todo = todoService.getTodoById(ID_1);

		assertTrue(TODO.get().equals(todo.get()));

		Mockito.verify(todoRepository, Mockito.times(1)).findById(ID_1);
	}

	@Test
	public void testCreateTodo() {
		Todo todo = todoService.createTodo(NEW_TODO);

		assertTrue(NEW_TODO.equals(todo));

		Mockito.verify(todoRepository, Mockito.times(1)).save(NEW_TODO);
	}

}
