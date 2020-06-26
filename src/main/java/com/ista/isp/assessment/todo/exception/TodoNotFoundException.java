package com.ista.isp.assessment.todo.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(final String todoId) {
        super(String.format("Todo task with id %s not found", todoId));
    }
}