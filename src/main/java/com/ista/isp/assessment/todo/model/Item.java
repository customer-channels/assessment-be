package com.ista.isp.assessment.todo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Fernando Moreno Ruiz
 * It represent an Item of a Todolist. 
 * */
@Data
@Entity
@Table(name="items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="checked")
	private boolean checked = false;
	
	public Item() {}
	
	public Item(long id, String title, String description, boolean checked) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.checked = checked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(checked, description, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return checked == other.checked && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", checked=" + checked + "]";
	}
	
	
	
}
