package com.ista.isp.assessment.todo.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoListController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoListControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getInitiallyEmptyList() throws Exception {
        mvc.perform(get("/get")).andExpect(content().string("[]"));
    }

    @Test
    @Order(2)
    void checkNonExistingTask() throws Exception {
        mvc.perform(put("/check/9")).andExpect(content().string("false"));
    }

    @Test
    @Order(3)
    void addNewTask() throws Exception {
        mvc.perform(post("/add/NewTaskDescription")).andExpect(content().string("true"));
    }

    @Test
    @Order(4)
    void checkExistingTask() throws Exception {
        mvc.perform(put("/check/0")).andExpect(content().string("true"));
    }

    @Test
    @Order(5)
    void getListWithAnItem() throws Exception {
        String expected = "[{\"id\":0,\"description\":\"NewTaskDescription\",\"checked\":true}]";
        mvc.perform(get("/get")).andExpect(content().string(expected));
    }

    @Test
    @Order(6)
    void remove() throws Exception {
        mvc.perform(delete("/remove/0")).andExpect(content().string("true"));
    }
}