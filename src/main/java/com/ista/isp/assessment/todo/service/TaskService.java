package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.exception.TaskNotFoundException;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.repository.TaskRepository;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(final Long id) throws TaskNotFoundException {
       return taskRepository.findById(id)
               .orElseThrow(() -> new TaskNotFoundException("Can't get task by id: " + id));
    }

    public Task addTask(final Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(final Long id, final Task task) throws TaskNotFoundException {
        var storedTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Can't updated task by id: " + id));
        storedTask.setName(task.getName());
        storedTask.setChecked(task.isChecked());
        storedTask.setDescription(task.getDescription());
        storedTask.setDeadline(task.getDeadline());
        return taskRepository.save(storedTask);
    }

    public void deleteTask(final Long id) throws TaskNotFoundException {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new TaskNotFoundException("Can't delete task with id: " + id);
        }
    }
}
