package com.ista.isp.assessment.todo.repositories;

import com.ista.isp.assessment.todo.entities.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask, Integer> {

}
