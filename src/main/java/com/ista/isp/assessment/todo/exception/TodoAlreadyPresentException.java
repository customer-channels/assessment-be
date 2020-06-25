package com.ista.isp.assessment.todo.exception;

public class TodoAlreadyPresentException extends RuntimeException {

    private static final long serialVersionUID = 6795058806636936573L;

    public TodoAlreadyPresentException(final String todoTitle) {
        super(String.format("Todo task %s already exists", todoTitle));
    }
}