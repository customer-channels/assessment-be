package com.ista.isp.assessment.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="text", nullable=false)
	private String text;

	@Column(name="checked", nullable=false)
	private Boolean checked;

}
