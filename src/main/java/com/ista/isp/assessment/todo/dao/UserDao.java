package com.ista.isp.assessment.todo.dao;

import com.ista.isp.assessment.todo.model.User;

public interface UserDao {
	/**
	 * Method used to save an user
	 * @param user
	 * @return
	 */
	User saveUser(User user);
	
	/**
	 * Method used to update data of an user
	 * @param user
	 * @return
	 */
	User updateUser(User user);
	
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
	User deleteUserById(int id);
	
	

}