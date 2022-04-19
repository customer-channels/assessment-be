package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.User;

public interface UserService {

	/**
	 * Method used to save an user
	 * @param user
	 * @return
	 */
	public User saveUser (User user);

	/**
	 * Method used to update data of an user
	 * @param user
	 * @return
	 */
	public User updateUser(User user);
	
	/**
	 * Method used to get an user by id
	 * @param id
	 * @return
	 */
	User getUserById(int id);
	
	/**
	 * Method used to delete an user by id
	 * @param id
	 * @return
	 */
	boolean deleteUserById(int id);
}
