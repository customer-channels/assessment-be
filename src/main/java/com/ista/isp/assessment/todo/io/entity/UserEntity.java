package com.ista.isp.assessment.todo.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 7034586675815198820L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false,length=20)
	private String firstName;
	
	@Column(nullable=false,length=30)
	private String lastName;
	
	@Column(nullable=false,length=100, unique=true)
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
