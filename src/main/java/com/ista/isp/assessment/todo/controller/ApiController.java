package com.ista.isp.assessment.todo.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.model.User;
import com.ista.isp.assessment.todo.service.UserService;

@RestController
@RequestMapping("/apiController")
public class ApiController {

	@Resource
	UserService userService;

	@PostMapping(value = "/saveUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity <User> saveUser(@RequestBody User user) throws Exception {
		User response = userService.saveUser(user);
		return  new ResponseEntity <User>(response, HttpStatus.CREATED);

	}

	@PutMapping(value = "/updateUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity <User> updateUser(@RequestBody User user) {
		User response = userService.updateUser(user);
		return  new ResponseEntity <User>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/getUserById", consumes = "application/json", produces = "application/json")
	public ResponseEntity <User> getUserById(@RequestParam int id) {
		User response =  userService.getUserById(id);
		return  new ResponseEntity <User>(response, HttpStatus.FOUND);

	}


	@DeleteMapping(value = "/deleteUserById", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> deleteUserById(@RequestParam int id) {
		Boolean response =  userService.deleteUserById(id);
		return  new ResponseEntity <Boolean>(response, HttpStatus.OK);

	}

}
