package com.ista.isp.assessment.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ista.isp.assessment.todo.dto.TaskRsDto;
import com.ista.isp.assessment.todo.entities.TodoTask;
import com.ista.isp.assessment.todo.repositories.TodoTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class TodoApplicationTests {

	private final String TODO_LIST_REST_URI = "/todo-tasks";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	TodoTaskRepository todoTaskRepository;

	@Test
	void testCreationOfNewItem() throws Exception {
		String taskDescription = "Description of a test task.";
		boolean checked = false;
		TaskRsDto todoTask = TaskRsDto.builder().taskDescription(taskDescription).checked(checked).build();

		// test unchecked.
		MvcResult postResult = mockMvc.perform(post(TODO_LIST_REST_URI, 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(todoTask)))
					.andExpect(status().isOk())
					.andReturn();

		TaskRsDto newTodoTask = objectMapper.readValue(postResult.getResponse().getContentAsString(), TaskRsDto.class);

		// check the response is ok.
		assertThat(newTodoTask.getTaskDescription()).isEqualTo(taskDescription);
		assertThat(newTodoTask.getChecked()).isEqualTo(checked);

		// check the entity.
		TodoTask taskEntity = todoTaskRepository.findById(newTodoTask.getId())
				.orElseThrow(() -> new Exception("Item was not created."));
		assertThat(taskEntity.getDescription()).isEqualTo(taskDescription);
		assertThat(taskEntity.isChecked()).isEqualTo(checked);

		// test checked.
		checked = true;
		todoTask.setChecked(checked);
		postResult = mockMvc.perform(post(TODO_LIST_REST_URI, 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(todoTask)))
				.andExpect(status().isOk())
				.andReturn();

		newTodoTask = objectMapper.readValue(postResult.getResponse().getContentAsString(), TaskRsDto.class);

		// check the response is ok.
		assertThat(newTodoTask.getTaskDescription()).isEqualTo(taskDescription);
		assertThat(newTodoTask.getChecked()).isEqualTo(checked);

		// check the entity.
		taskEntity = todoTaskRepository.findById(newTodoTask.getId())
				.orElseThrow(() -> new Exception("Item was not created."));
		assertThat(taskEntity.getDescription()).isEqualTo(taskDescription);
		assertThat(taskEntity.isChecked()).isEqualTo(checked);
	}

	@Test
	void testUpdateOfExistingItems_CheckedIsNotDeleted() throws Exception {

		// first add an item.
		String taskDescription = "Description of a test task.";
		boolean checked = false;
		TaskRsDto todoTask = TaskRsDto.builder().taskDescription(taskDescription).checked(checked).build();

		MvcResult postResult = mockMvc.perform(post(TODO_LIST_REST_URI, 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(todoTask)))
				.andExpect(status().isOk())
				.andReturn();

		TaskRsDto newTodoTask = objectMapper.readValue(postResult.getResponse().getContentAsString(), TaskRsDto.class);

		// update the added item
		taskDescription = "Description of a test task updated.";
		checked = true;
		todoTask.setTaskDescription(taskDescription);
		todoTask.setChecked(checked);
		MvcResult putResult = mockMvc.perform(put(String.format("%s/%d", TODO_LIST_REST_URI, newTodoTask.getId()), 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(todoTask)))
					.andExpect(status().isOk())
					.andReturn();

		// check the response is ok.
		TaskRsDto updatedTodoTask = objectMapper.readValue(putResult.getResponse().getContentAsString(), TaskRsDto.class);
		assertThat(updatedTodoTask.getTaskDescription()).isEqualTo(taskDescription);
		assertThat(updatedTodoTask.getChecked()).isEqualTo(checked);

		// check the entity is ok and was not deleted.
		TodoTask updatedTaskEntity = todoTaskRepository.findById(updatedTodoTask.getId())
				.orElseThrow(() -> new Exception("Item does not exist."));
		assertThat(updatedTaskEntity.getDescription()).isEqualTo(taskDescription);
		assertThat(updatedTaskEntity.isChecked()).isEqualTo(checked);


	}

}
