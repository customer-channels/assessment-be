package com.ista.isp.assessment.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.isp.assessment.todo.dto.UserDto;
import com.ista.isp.assessment.todo.io.entity.UserEntity;
import com.ista.isp.assessment.todo.repo.UserRepository;
import com.ista.isp.assessment.todo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository  userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user,userEntity);
		
		UserEntity storedUser = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUser,returnValue);
		
		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new RuntimeException("Record Not Exists"); 

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserById(long id) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findById(id);
		
		if(userEntity == null ) throw new RuntimeException("Record Not Exists");
		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(long id,UserDto user) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findById(id);
		
		if(userEntity == null ) throw new RuntimeException("Record Not Exists");
		
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());

		
		UserEntity updatedUser = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatedUser,returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteUser(long id) {
		UserEntity userEntity = userRepository.findById(id);
		
		if(userEntity == null ) throw new RuntimeException("Record Not Exists");
				
		userRepository.delete(userEntity);		
		
		
	}

	@Override
	public List<UserDto> getAllUser() {
		List<UserDto> userDto  = new ArrayList<>();
		
		UserDto returnValue;
		Iterable<UserEntity> userEntity = userRepository.findAll();
		
		for(UserEntity user : userEntity) {
			returnValue = new UserDto();
			BeanUtils.copyProperties(user,returnValue);
			userDto.add(returnValue);
		}
		
		return userDto;
	}


	


}
