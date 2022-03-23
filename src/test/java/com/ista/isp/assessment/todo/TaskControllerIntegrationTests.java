package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TaskService service;

	@Test
	public void verifyGetAllRequestIntegrationTest() throws Exception {

		var task = new Task();
		task.setName("task1");
		task = service.addTask(task);

		try {
			mvc.perform(get("/api/task").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].id", is(1)))
					.andExpect(jsonPath("$[0].name", is("task1")))
					.andExpect(jsonPath("$[0].checked", is(false)))
					.andDo(print());
		} finally {
			service.deleteTask(task.getId());
		}

	}

	@Test
	public void verifyNotFoundErrorMessageForGetByIdIntegrationTest() throws Exception {

		mvc.perform(get("/api/task/42").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Can't get task by id: 42")))
				.andDo(print());

	}

}
