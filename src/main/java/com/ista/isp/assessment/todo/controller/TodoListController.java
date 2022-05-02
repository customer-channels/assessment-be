package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.model.TodoListModel;
import com.ista.isp.assessment.todo.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/todoList")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @GetMapping
    public List<TodoListModel> findAll() {
        return  todoListRepository.findAll();
    }

    @PostMapping
    public TodoListModel save(@Valid @NotNull @RequestBody TodoListModel todoListModel) {
        return todoListRepository.save(todoListModel);
    }

    @PutMapping
    public TodoListModel update(@Valid @NotNull @RequestBody TodoListModel todoListModel) {
        return todoListRepository.save(todoListModel);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        todoListRepository.deleteById(id);
    }
}
