package com.ista.isp.assessment.todo.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.dto.UserDto;
import com.ista.isp.assessment.todo.repo.UserRepository;
import com.ista.isp.assessment.todo.service.UserService;
import com.ista.isp.assessment.todo.ui.model.request.UserDetailsRequestModel;
import com.ista.isp.assessment.todo.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // https://localhost:8080/users/
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable long id) {
		UserRest returnValue = new UserRest();
		
		UserDto userDto = userService.getUserById(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@GetMapping
	public List<UserRest> getAllUser() {
		List<UserRest> listReturnValue  = new ArrayList<>();
		List<UserDto> listUserDto  = new ArrayList<>();
		UserRest userRest;
		listUserDto = userService.getAllUser();
		for(UserDto user : listUserDto) {
			userRest = new UserRest();
			BeanUtils.copyProperties(user, userRest);
			listReturnValue.add(userRest);
		}

		return listReturnValue;
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		
		if (userRepository.findByEmail(userDetails.getEmail()) != null) throw new RuntimeException("Record Already Exists");
		
		UserRest userValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto userCreated = userService.createUser(userDto);
		BeanUtils.copyProperties(userCreated, userValue);

		return userValue;
	}

	@PutMapping(path = "/{id}")
	public UserRest updateUser(@PathVariable long id, @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest userValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto userUpdate = userService.updateUser(id,userDto);
		BeanUtils.copyProperties(userUpdate, userValue);

		return userValue;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return "User Deleted";
	}
}
