package com.ista.isp.assessment.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class TodoListModel {

    private Long id;
    @NotBlank
    private String title;
    private boolean checked;

    public TodoListModel() {
    }

    public TodoListModel(Long id, String title, boolean checked) {
        this.id = id;
        this.title = title;
        this.checked = checked;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean done) {
        this.checked = done;
    }
}
