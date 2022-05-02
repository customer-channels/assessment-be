package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.model.TodoListModel;
import com.ista.isp.assessment.todo.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TodoListControllerTest {

    @Mock
    TodoListRepository repository;

    @Mock
    TodoListModel listModel;

    @BeforeEach
    void setup() {
        when(listModel.getId()).thenReturn(1L);
        when(listModel.getTitle()).thenReturn("ToDo refactor existed code");
        when(listModel.isChecked()).thenReturn(false);
    }

    @Test
    void saveTodoListTask() {
        repository.save(listModel);
        verify(repository, times(1)).save(listModel);
    }

    @Test
    void updateTodoListTask() {
        when(listModel.isChecked()).thenReturn(true);
        repository.save(listModel);
        verify(repository, times(1)).save(listModel);
        assertTrue(listModel.isChecked());
    }

    @Test
    void getTodoListById() {
        repository.findAll();
        verify(repository, times(1)).findAll();
        assertEquals("ToDo refactor existed code", listModel.getTitle());
        assertFalse(listModel.isChecked());
    }

    @Test
    void deleteTodoList() {
        repository.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
