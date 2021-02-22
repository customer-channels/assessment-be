package com.ista.isp.assessment.todo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoTaskDTO {

	private Long id;
	
	@NotNull
	@Size(max = 30)
	private String description;

	@NotNull
	private Boolean done;

	public TodoTaskDTO() {

	}

	public TodoTaskDTO(String description, Boolean done) {
		super();
		this.description = description;
		this.done = done;
	}
	
	public TodoTaskDTO(Long id, String description, Boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.done = done;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	@Override
	public String toString() {
		return "TodoTaskDTO [id=" + id + ", description=" + description + ", done=" + done + "]";
	}
}
