package com.ista.isp.assessment.todo.daos;

import com.ista.isp.assessment.todo.cache.TodoCache;
import com.ista.isp.assessment.todo.dtos.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class TodoDao {

    @Autowired
    private TodoCache cache;

    public Set<TodoDto> getAll() {
        return this.cache.getData();
    }

    public TodoDto add(TodoDto todo) {
        this.cache.getData().add(todo);
        return todo;
    }

    public void delete(TodoDto todo) {
        this.cache.getData().remove(todo);
    }

    public int getId() {
        return TodoCache.generate();
    }

}
