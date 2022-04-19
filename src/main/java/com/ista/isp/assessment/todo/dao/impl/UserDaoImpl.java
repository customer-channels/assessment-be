package com.ista.isp.assessment.todo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ista.isp.assessment.todo.dao.UserDao;
import com.ista.isp.assessment.todo.model.User;

@Repository("UserDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private final String INIT_METHOD = "-->";
	private final String END_METHOD = "<--";


	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Override
	public User saveUser(User user) {
		String methodName = "createUser";
		logger.debug(INIT_METHOD + methodName);
		
		 final String query = "insert into users(name, address, email, checked)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("address", user.getAddress()).addValue("email", user.getEmail())
				.addValue("checked", user.isChecked());

		template.update(query, parameters, keyHolder);
		user.setId(keyHolder.getKey().intValue());
		
		logger.debug(END_METHOD + methodName);
		return user;
	}
	
	@Override
	public User updateUser(User user) {
		String methodName = "createUser";
		logger.debug(INIT_METHOD + methodName);
		 final String query = "update users users(name, address, email, checked)";
		 
		 user.setChecked(true);
		 
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("address", user.getAddress()).addValue("email", user.getEmail())
				.addValue("checked", user.isChecked());
		template.update(query, parameters, keyHolder);
		
		logger.debug(END_METHOD + methodName);
		return user;
	}

	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}