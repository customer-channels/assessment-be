package com.ista.isp.assessment.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ista.isp.assessment.todo.entity.TodoTaskEntity;

@Repository
public interface ITodoTaskRepository extends JpaRepository<TodoTaskEntity, Long> {

}
