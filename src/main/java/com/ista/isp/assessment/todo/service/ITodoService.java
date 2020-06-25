package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.Todo;

import java.util.Set;

public interface ITodoService {

    Set<Todo> getAll();

    Todo add(Todo todo);

    void delete(Integer id);

}
