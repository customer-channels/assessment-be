package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @GetMapping
    @ResponseBody
    public Set<Todo> getAll() {
        return this.todoService.getAll();
    }

    @PostMapping
    @ResponseBody
    public Todo add(@Valid @RequestBody Todo todo) {
        return this.todoService.add(todo);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable @Min(1) Integer id) {
         this.todoService.delete(id);
    }

}
