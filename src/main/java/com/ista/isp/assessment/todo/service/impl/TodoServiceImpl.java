package com.ista.isp.assessment.todo.service.impl;

import com.ista.isp.assessment.todo.dao.TodoDao;
import com.ista.isp.assessment.todo.exception.TodoAlreadyPresentException;
import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TodoServiceImpl implements ITodoService {

        @Autowired
        private TodoDao todoDao;

        public Set<Todo> getAll() {
            return this.todoDao.getAll();
        }

        public synchronized Todo add(Todo addedTodo) {

            if (todoDao.getAll().stream().anyMatch(
                    todo -> todo.getTitle().equals(addedTodo.getTitle()))){
                throw new TodoAlreadyPresentException(addedTodo.getTitle());
            } else {
                addedTodo.setId(todoDao.getId());
                return todoDao.add(addedTodo);
            }

        }

        public synchronized void delete(Integer todoId) {
            Todo todoToDelete = todoDao.getAll().stream().filter(
                    todo -> todo.getId().equals(todoId)).findFirst().get();
            this.todoDao.delete(todoToDelete);
        }



}
