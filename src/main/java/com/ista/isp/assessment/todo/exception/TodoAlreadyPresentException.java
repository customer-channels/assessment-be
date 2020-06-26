package com.ista.isp.assessment.todo.exception;

public class TodoAlreadyPresentException extends RuntimeException {

    public TodoAlreadyPresentException(final String todoTitle) {
        super(String.format("Todo task %s already exists", todoTitle));
    }
}