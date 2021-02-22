package com.ista.isp.assessment.todo.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class TodoTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "done")
	private Boolean done;

	public TodoTaskEntity() {

	}
	
	public TodoTaskEntity(Boolean done) {
		this.done = done;
	}

	public TodoTaskEntity(String description, Boolean done) {
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
}
