package com.ista.isp.assessment.todo.controllers;

import com.ista.isp.assessment.todo.dtos.TodoDto;
import com.ista.isp.assessment.todo.services.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @GetMapping
    @ResponseBody
    public Set<TodoDto> getAll() {
        return this.todoService.getAll();
    }

    @PostMapping
    @ResponseBody
    public TodoDto add(@RequestBody TodoDto todo) {
        return this.todoService.add(todo);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id) {
         this.todoService.delete(id);
    }

}
