package com.ista.isp.assessment.todo.services.impl;

import com.ista.isp.assessment.todo.daos.TodoDao;
import com.ista.isp.assessment.todo.dtos.TodoDto;
import com.ista.isp.assessment.todo.services.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TodoServiceImpl implements ITodoService {

        @Autowired
        private TodoDao todoDao;

        public Set<TodoDto> getAll() {
            return this.todoDao.getAll();
        }

        public TodoDto add(TodoDto addedTodo) {
            int id = todoDao.getAll().size();
            addedTodo.setId(todoDao.getId());
            return todoDao.add(addedTodo);
        }

        public void delete(Integer todoId) {
            TodoDto todoToDelete = todoDao.getAll().stream().filter(
                    todo -> todo.getId().equals(todoId)).findFirst().get();
            this.todoDao.delete(todoToDelete);
        }



}
