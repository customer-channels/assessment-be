package com.ista.isp.assessment.todo.controllers;

import com.ista.isp.assessment.todo.model.TodoItem;
import com.ista.isp.assessment.todo.model.TodoList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {

    private TodoList todoList;

    public TodoListController() {
        this.todoList = new TodoList();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public List<TodoItem> getList() {
        return this.todoList.getItems();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add/{description}")
    public boolean add(@PathVariable("description") String description) {
        return this.todoList.addItem(description.trim());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/check/{id}")
    public boolean check(@PathVariable("id") long id) {
        return this.todoList.checkItem(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable("id") long id) {
        return this.todoList.removeItem(id);
    }

}
