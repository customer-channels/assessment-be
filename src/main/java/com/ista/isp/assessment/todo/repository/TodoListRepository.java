package com.ista.isp.assessment.todo.repository;

import com.ista.isp.assessment.todo.model.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListModel, Long> {
}
