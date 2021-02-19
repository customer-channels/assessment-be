package com.ista.isp.assessment.todo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ista.isp.assessment.todo.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findById(long Id);
}
