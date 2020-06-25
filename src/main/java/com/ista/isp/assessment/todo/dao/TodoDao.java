package com.ista.isp.assessment.todo.dao;

import com.ista.isp.assessment.todo.cache.TodoCache;
import com.ista.isp.assessment.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class TodoDao {

    @Autowired
    private TodoCache cache;

    public Set<Todo> getAll() {
        return this.cache.getData();
    }

    public Todo add(Todo todo) {
        this.cache.getData().add(todo);
        return todo;
    }

    public void delete(Todo todo) {
        this.cache.getData().remove(todo);
    }

    public int getId() {
        return TodoCache.generate();
    }

}
