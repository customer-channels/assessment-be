package com.ista.isp.assessment.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ista.isp.assessment.todo.dto.TodoTaskDTO;
import com.ista.isp.assessment.todo.exception.TaskNotFoundException;
import com.ista.isp.assessment.todo.service.ITodoTaskService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoTaskControllerTest {

	private static final String URI = "/todotasks/";
	private static final String URI_ID = "/todotasks/1";
	private static final String ERROR_MESSAGE = "Todo Task with ID '1' NOT FOUND";

	private TodoTaskDTO todoTaskToCheck;
	private List<TodoTaskDTO> taskCheckDoneTrueList;
	private List<TodoTaskDTO> taskCheckDoneFalseList;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectmapper;

	@Autowired
	private ITodoTaskService todoTaskService;

	@BeforeAll
	private void createTaskList() {
		todoTaskToCheck = new TodoTaskDTO(Long.valueOf(1), "description 01", false);

		// We need 2 lists for test later the getDoneTask() method
		taskCheckDoneTrueList = new ArrayList<TodoTaskDTO>();
		taskCheckDoneFalseList = new ArrayList<TodoTaskDTO>();
		
		taskCheckDoneFalseList.add(todoTaskToCheck);
		taskCheckDoneFalseList.add(new TodoTaskDTO("description 02", false));
		taskCheckDoneTrueList.add(new TodoTaskDTO("description 03", true));
		taskCheckDoneTrueList.add(new TodoTaskDTO("description 04", true));
		taskCheckDoneTrueList.add(new TodoTaskDTO("description 05", true));

		taskCheckDoneFalseList.forEach(s -> todoTaskService.createTodoTask(s));
		taskCheckDoneTrueList.forEach(s -> todoTaskService.createTodoTask(s));
	}

	private TodoTaskDTO getTodoTaskDtoFromResult(ResultActions result) throws Exception {
		return objectmapper.readValue(result.andReturn().getResponse().getContentAsString(), TodoTaskDTO.class);
	}

	@Test
	@Order(0)
	public void createTaskTest() throws Exception {
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.post(URI).content(objectmapper.writeValueAsString(todoTaskToCheck))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

		TodoTaskDTO taskCreated = getTodoTaskDtoFromResult(result);
		assertNotNull(taskCreated.getId());
		assertEquals(todoTaskToCheck.getDescription(), taskCreated.getDescription());
		assertEquals(todoTaskToCheck.getDone(), taskCreated.getDone());
	}

	@Test
	@Order(1)
	public void getTaskById() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(URI_ID)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		TodoTaskDTO taskFound = getTodoTaskDtoFromResult(result);
		assertEquals(todoTaskToCheck.getId(), taskFound.getId());
		assertEquals(todoTaskToCheck.getDescription(), taskFound.getDescription());
		assertEquals(todoTaskToCheck.getDone(), taskFound.getDone());
	}

	@Test
	@Order(2)
	public void getDoneTask() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "done")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		assertEquals(taskCheckDoneTrueList.size(), taskListSize(result));
	}

	@Test
	@Order(3)
	public void getAllTasks() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		assertEquals(taskCheckDoneTrueList.size() + taskCheckDoneFalseList.size(), taskListSize(result));
	}

	@Test
	@Order(4)
	public void updateTaskTest() throws Exception {
		String description = "description UPDATED";
		TodoTaskDTO taskDtoUpdated = new TodoTaskDTO(description, false);

		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.put(URI_ID).content(objectmapper.writeValueAsString(taskDtoUpdated))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

		TodoTaskDTO taskFound = getTodoTaskDtoFromResult(result);
		assertEquals(todoTaskToCheck.getId(), taskFound.getId());
		assertEquals(description, taskFound.getDescription());
		assertEquals(todoTaskToCheck.getDone(), taskFound.getDone());
	}

	@Test
	@Order(5)
	public void deleteTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(URI_ID)).andExpect(status().isNoContent())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(6)
	public void deleteAllTasks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(URI)).andExpect(status().isNoContent())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(7)
	public void getTaskNotFoundException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(URI_ID)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFoundException))
				.andExpect(result -> assertEquals(ERROR_MESSAGE, result.getResolvedException().getMessage()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(8)
	public void updateTaskNotFoundException() throws Exception {
		String description = "description UPDATED";
		TodoTaskDTO taskDtoUpdated = new TodoTaskDTO(description, false);

		mockMvc.perform(MockMvcRequestBuilders.put(URI_ID).content(objectmapper.writeValueAsString(taskDtoUpdated))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFoundException))
				.andExpect(result -> assertEquals(ERROR_MESSAGE, result.getResolvedException().getMessage()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(9)
	public void deleteTaskNotFoundException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(URI_ID)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFoundException))
				.andExpect(result -> assertEquals(ERROR_MESSAGE, result.getResolvedException().getMessage()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(10)
	public void nullDoneMethodArgumentNotValidException() throws Exception {
		TodoTaskDTO task = new TodoTaskDTO("description", null);
		mockMvc.perform(MockMvcRequestBuilders
				.post(URI).content(objectmapper.writeValueAsString(task)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(
						result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Order(11)
	public void descriptionMaxLengthMethodArgumentNotValidException() throws Exception {
		TodoTaskDTO task = new TodoTaskDTO("0123456789012345678901234567890123456789", true);
		mockMvc.perform(MockMvcRequestBuilders
				.post(URI).content(objectmapper.writeValueAsString(task)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(
						result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
				.andDo(MockMvcResultHandlers.print());
	}

	private int taskListSize(ResultActions result) throws Exception {
		Gson gson = new Gson();
		String jsonString = result.andReturn().getResponse().getContentAsString();
		Type todoTaskType = new TypeToken<List<TodoTaskDTO>>() {
		}.getType();
		List<TodoTaskDTO> todoTaskList = gson.fromJson(jsonString, todoTaskType);
		return todoTaskList.size();
	}
}