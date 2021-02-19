package com.ista.isp.assessment.todo.service;

import java.util.List;

import com.ista.isp.assessment.todo.dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserById(long id);
	UserDto updateUser(long id,UserDto user);
	List<UserDto> getAllUser();
	void deleteUser(long id);
}
