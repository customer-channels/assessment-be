package com.ista.isp.assessment.todo;

import com.google.gson.Gson;
import com.ista.isp.assessment.todo.controller.TodoController;
import com.ista.isp.assessment.todo.model.Todo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoController controller;

    private final Gson gsonMapper = new Gson();

    @BeforeAll
    public void contextLoads() throws Exception {
        assertThat(mockMvc).isNotNull();
        assertThat(controller).isNotNull();
        assertThat(this.gsonMapper).isNotNull();
    }

    @Test
    @Order(1)
    void testGetAll() throws Exception {

        Todo todo = Todo.builder()
                .title("Test your API profusely").build();

        final String jsonBody = this.gsonMapper.toJson(todo, Todo.class);


        final MockHttpServletResponse response = mockMvc.perform(get("/todo")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isOk()).andReturn().getResponse();


        final Todo[] responseModel =
                this.gsonMapper.fromJson(new String(response.getContentAsByteArray()), Todo[].class);

        assertThat(responseModel).isNotNull();
        assertThat(responseModel.length == 3).isNotNull();


    }

    @Test
    @Order(2)
    void testAddTodo() throws Exception {

        Todo todo = Todo.builder()
                .title("Test your API profusely").build();

        final String jsonBody = this.gsonMapper.toJson(todo, Todo.class);


        final MockHttpServletResponse response = mockMvc.perform(post("/todo")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isOk()).andReturn().getResponse();


        final Todo responseModel =
                this.gsonMapper.fromJson(new String(response.getContentAsByteArray()), Todo.class);

        assertThat(responseModel).isNotNull();
        assertThat(responseModel.getId()).isNotNull();


    }

    @Test
    @Order(3)
    void testAddTodoAlreadyPresent() throws Exception {

        Todo todo = Todo.builder()
                .title("Make your daily training").build();

        final String jsonBody = this.gsonMapper.toJson(todo, Todo.class);


        final MockHttpServletResponse response = mockMvc.perform(post("/todo")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isBadRequest()).andReturn().getResponse();


    }

    @Test
    @Order(4)
    void testAddTodoEmpty() throws Exception {

        Todo todo = Todo.builder()
                .title("").build();

        final String jsonBody = this.gsonMapper.toJson(todo, Todo.class);


        final MockHttpServletResponse response = mockMvc.perform(post("/todo")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isBadRequest()).andReturn().getResponse();

    }

    @Test
    @Order(4)
    void deleteTodo() throws Exception {


        final String jsonBody = "";


        final MockHttpServletResponse response = mockMvc.perform(delete("/todo/1")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isOk()).andReturn().getResponse();

    }

    @Test
    @Order(5)
    void deleteTodoNotFound() throws Exception {

        final String jsonBody = "";


        final MockHttpServletResponse response = mockMvc.perform(delete("/todo/100")
                .contentType("application/json")
                .content(jsonBody))
                .andExpect(status().isNotFound()).andReturn().getResponse();


    }


}
