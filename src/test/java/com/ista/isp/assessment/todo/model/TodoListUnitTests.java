package com.ista.isp.assessment.todo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListUnitTests {

    @Test
    void addItemShouldSucceedOnEmptyList() {
        TodoList list = new TodoList();                                     // Given
        boolean added = list.addItem("Item1");                     // When
        String itemDescription = list.getItems().get(0).getDescription();

        assertTrue(added);                                                  // Then
        assertEquals("Item1", itemDescription);
    }

    @Test
    void addItemShouldNotAddTheSameOneTwice() {
        TodoList list = new TodoList();
        boolean added1 = list.addItem("Item1");
        boolean added2 = list.addItem("Item1");
        int listSize = list.getItems().size();

        assertTrue(added1);
        assertFalse(added2);
        assertEquals(1, listSize);
    }

    @Test
    void checkExistingItemShouldSucceed() {
        TodoList list = new TodoList();
        boolean added = list.addItem("Item1");
        boolean checked = list.checkItem(0);
        TodoItem item = list.getItems().get(0);

        assertTrue(added);
        assertTrue(checked);
        assertTrue(item.isChecked());
    }

    @Test
    void checkExistingItemTwiceShouldUncheckItSuccessfully() {
        TodoList list = new TodoList();
        boolean added = list.addItem("Item1");
        boolean checked1 = list.checkItem(0);
        boolean checked2 = list.checkItem(0);
        TodoItem item = list.getItems().get(0);

        assertTrue(added);
        assertTrue(checked1);
        assertTrue(checked2);
        assertFalse(item.isChecked());
    }

    @Test
    void checkNonExistingItemShouldFail() {
        TodoList list = new TodoList();
        boolean checked = list.checkItem(0);

        assertFalse(checked);
    }

    @Test
    void removeExistingItemShouldSucceed() {
        TodoList list = new TodoList();
        boolean added = list.addItem("Item1");
        boolean removed = list.removeItem(0);
        int listSize = list.getItems().size();

        assertTrue(added);
        assertTrue(removed);
        assertEquals(0, listSize);
    }

    @Test
    void removeNonExistingItemShouldFail() {
        TodoList list = new TodoList();
        boolean removed = list.removeItem(0);

        assertFalse(removed);
    }

    @Test
    void getItemsShouldReturnEmptyListIfNoItemIsAdded() {
        TodoList list = new TodoList();
        List<TodoItem> items = list.getItems();

        assertEquals(new ArrayList<TodoItem>(), items);
    }

    @Test
    void getItemsShouldReturnListOfItemsAfterAddingSome() {
        TodoList list = new TodoList();
        boolean added = list.addItem("Test Item");
        List<TodoItem> items = list.getItems();

        assertTrue(added);
        assertEquals(1, items.size());
    }
}