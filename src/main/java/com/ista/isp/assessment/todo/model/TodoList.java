package com.ista.isp.assessment.todo.model;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<TodoItem> items;
    private long lastCounter = 0;

    public TodoList() {
        this.items = new ArrayList<TodoItem>();
    }

    public boolean addItem(String itemName) {
        if (itemName.length() == 0) {
            return false;
        }
        TodoItem item = findItem(itemName);
        if (item != null && this.items.contains(item)) {
            return false;
        }
        this.items.add(new TodoItem(this.lastCounter, itemName));
        this.lastCounter++;
        return true;
    }

    public boolean checkItem(long id) {
        TodoItem item = findItemById(id);
        if (item == null || !this.items.contains(item)) {
            return false;
        }
        item.setChecked(!item.isChecked());
        return true;
    }

    public boolean removeItem(long id) {
        TodoItem item = findItemById(id);
        if (item == null || !this.items.contains(item)) {
            return false;
        }
        this.items.remove(item);
        return true;
    }

    private TodoItem findItem(String itemName) {
        if (this.items.isEmpty()) {
            return null;
        }
        for (TodoItem item : this.items) {
            if (item != null && item.getDescription().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    private TodoItem findItemById(long id) {
        if (this.items.isEmpty()) {
            return null;
        }
        for (TodoItem item : this.items) {
            if (item != null && item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<TodoItem> getItems() {
        return this.items;
    }

}
