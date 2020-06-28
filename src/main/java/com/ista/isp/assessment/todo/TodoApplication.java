package com.ista.isp.assessment.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ista.isp.assessment.todo.entity.Todo;
import com.ista.isp.assessment.todo.repository.TodoRepository;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Todo todo1 = new Todo();
		todo1.setName("First todo");
		todo1.setSelected(false);
		todoRepository.save(todo1);
		Todo todo2 = new Todo();
		todo2.setName("Second todo");
		todo2.setSelected(false);
		todoRepository.save(todo2);
	}
}
