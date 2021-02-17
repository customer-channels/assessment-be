package com.ista.isp.assessment.todo.model;

public class TodoItem {

    private final long id;
    private final String description;
    private Boolean checked;

    public TodoItem(long id, String description) {
        this.id = id;
        this.description = description;
        this.checked = false;
    }

    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
