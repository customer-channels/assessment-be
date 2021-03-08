package com.ista.isp.assessment.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.ista.isp.assessment.todo.exception.TaskException;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.repository.TaskRepository;
import com.ista.isp.assessment.todo.service.impl.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

	@InjectMocks
	private TaskServiceImpl taskServiceImp = new TaskServiceImpl();

	@Mock
	private TaskRepository taskRepository;

	@Test
	public void testFindAllTasksByPropertiesWithoutFilters() {

		// Mocks
		List<String> filter = new ArrayList<>();
		List<Task> tasks = new ArrayList<>();
		tasks.add(createTask());

		Mockito.when(taskRepository.findAllByOrderByCheckedDesc()).thenReturn(tasks);

		// Test
		List<Task> result = taskServiceImp.findAllTasksByProperties(filter);

		// Verifies
		assertEquals(1, result.size());
	}

	@Test
	public void testFindAllTasksByPropertiesWithFilters() {

		// Mocks
		List<String> filter = new ArrayList<>();
		filter.add("text:A");
		List<Task> tasks = new ArrayList<>();
		tasks.add(createTask());

		Mockito.when(taskRepository.findAll(Mockito.any(Specification.class))).thenReturn(tasks);

		// Test
		List<Task> result = taskServiceImp.findAllTasksByProperties(filter);

		// Verifies
		assertEquals(1, result.size());
	}

	@Test
	public void testGetTask() {

		// Mocks
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(createTask()));

		// Test
		Long taskId = 1L;
		Task result = taskServiceImp.getTask(taskId);

		// Verifies
		assertNotNull(result);
		assertEquals(taskId, result.getId());
	}

	@Test
	public void testGetTaskNotFound() {

		// Verifies
		assertThrows(TaskException.class, () -> taskServiceImp.getTask(0L));
	}

	@Test
	public void testCreateTask() {

		// Mocks
		Task task = createTask();

		Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

		// Test
		Task result = taskServiceImp.createTask(task);

		// Verifies
		assertEquals(task.getText(), result.getText());
		assertEquals(task.getChecked(), result.getChecked());
		assertNotNull(result.getId());
	}

	@Test
	public void testUpdateTask() {

		// Mocks
		Task task = createTask();
		Mockito.when(taskRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

		// Test
		Task result = taskServiceImp.updateTask(task.getId(), task);

		// Verifies
		assertEquals(task, result);
	}

	@Test
	public void testUpdateTaskMismatchIds() {

		Long diffTaskId = 0L;
		Task task = createTask();

		// Verifies
		assertThrows(TaskException.class, () -> taskServiceImp.updateTask(diffTaskId, task));
	}

	@Test
	public void testDeleteTask() {

		// Mocks
		Mockito.when(taskRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);

		// Test
		taskServiceImp.deleteTask(1L);

		// Verifies
		verify(taskRepository, times(1)).deleteById(Mockito.eq(1L));
	}

	private Task createTask() {
		Task task = new Task();
		task.setId(1L);
		task.setText("Task A");
		task.setChecked(Boolean.TRUE);

		return task;
	}
}
