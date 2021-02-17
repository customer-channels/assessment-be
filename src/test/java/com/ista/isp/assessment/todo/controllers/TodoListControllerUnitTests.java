package com.ista.isp.assessment.todo.controllers;

import com.ista.isp.assessment.todo.model.TodoItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListControllerUnitTests {

    @Test
    void getList() {
        TodoListController controller = new TodoListController();   // Given
        List<TodoItem> response = controller.getList();             // When
        List<TodoItem> expected = new ArrayList<TodoItem>();

        assertEquals(expected, response);                           // Then
    }

    @Test
    void add() {
        TodoListController controller = new TodoListController();
        boolean added = controller.add("Testing task");
        List<TodoItem> items = controller.getList();

        assertTrue(added);
        assertEquals("Testing task", items.get(0).getDescription());
    }

    @Test
    void check() {
        TodoListController controller = new TodoListController();
        boolean added = controller.add("Testing task 1");
        boolean checked = controller.check(0);
        List<TodoItem> items = controller.getList();

        assertTrue(checked);
        assertTrue(added);
        assertEquals("Testing task 1", items.get(0).getDescription());
    }

    @Test
    void remove() {
        TodoListController controller = new TodoListController();
        boolean added = controller.add("Testing task 2");
        int itemsBefore = controller.getList().size();
        boolean removed = controller.remove(0);
        int itemsAfter = controller.getList().size();

        assertTrue(added);
        assertTrue(removed);
        assertEquals(1, itemsBefore);
        assertEquals(0, itemsAfter);
    }
}