package com.ista.isp.assessment.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

	@InjectMocks
	private TaskController taskController = new TaskController();

	@Mock
	private TaskService taskService;

	@Test
	public void testGetTasks() {

		// Mocks
		List<String> filter = new ArrayList<>();
		List<Task> tasks = new ArrayList<>();
		tasks.add(createTask());

		Mockito.when(taskService.findAllTasksByProperties(filter)).thenReturn(tasks);

		// Test
		ResponseEntity<List<Task>> result = taskController.getTasks(filter);

		// Verifies
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(tasks.size(), result.getBody().size());
	}

	@Test
	public void testGetTask() {

		// Mocks
		Mockito.when(taskService.getTask(Mockito.anyLong())).thenReturn(createTask());

		// Test
		ResponseEntity<Task> result = taskController.getTask(1L);

		// Verifies
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
	}

	@Test
	public void testCreateTask() {

		// Mocks
		Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(createTask());

		// Test
		ResponseEntity<Task> result = taskController.createTask(createTask());

		// Verifies
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertNotNull(result.getBody());
	}

	@Test
	public void testUpdateTask() {

		// Mocks
		Mockito.when(taskService.updateTask(Mockito.anyLong(), Mockito.any(Task.class))).thenReturn(createTask());

		Task task = new Task();
		task.setId(1L);

		// Test
		ResponseEntity<Task> result = taskController.updateTask(1L, task);

		// Verifies
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
	}

	@Test
	public void testDeleteTask() {

		// Test
		ResponseEntity<Void> result = taskController.deleteTask(1L);

		// Verifies
		verify(taskService, times(1)).deleteTask(Mockito.eq(1L));
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		assertNull(result.getBody());
	}
	
	private Task createTask() {
		Task task = new Task();
		task.setId(1L);
		task.setText("Task A");
		task.setChecked(Boolean.TRUE);
		
		return task;
	}

}
