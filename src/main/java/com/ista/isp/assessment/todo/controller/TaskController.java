package com.ista.isp.assessment.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.dto.ErrorDTO;
import com.ista.isp.assessment.todo.exception.TaskException;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/task")
	public ResponseEntity<List<Task>> getTasks(@RequestParam(value = "filter", required = false) List<String> filter) {
	
		List<Task> tasks = taskService.findAllTasksByProperties(filter);

		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> getTask(@PathVariable Long id) {
				
		Task task = taskService.getTask(id);
		
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@PostMapping("/task")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
	
		Task creatededTask = taskService.createTask(task);
		
		return new ResponseEntity<Task>(creatededTask, HttpStatus.CREATED);
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
		
		Task updatedTask = taskService.updateTask(id, task);
						
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);

	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		
		taskService.deleteTask(id);
		
		return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(TaskException.class)
	public ResponseEntity<ErrorDTO> handleTaskException(TaskException taskException){        
        return new ResponseEntity<ErrorDTO>(taskException.getError(), taskException.getHttpStatus());
    }
}
