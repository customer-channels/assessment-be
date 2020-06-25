package com.ista.isp.assessment.todo.services;

import com.ista.isp.assessment.todo.dtos.TodoDto;

import java.util.Set;

public interface ITodoService {

    Set<TodoDto> getAll();

    TodoDto add(TodoDto todo);

    void delete(Integer id);

}
