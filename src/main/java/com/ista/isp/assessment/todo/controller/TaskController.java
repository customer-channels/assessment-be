package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.exception.TaskNotFoundException;
import com.ista.isp.assessment.todo.model.TaskRequestDTO;
import com.ista.isp.assessment.todo.model.TaskResponseDTO;
import com.ista.isp.assessment.todo.service.TaskMapper;
import com.ista.isp.assessment.todo.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(final TaskService taskService, final TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/api/task")
    public List<TaskResponseDTO> getAllTasks() {
        var list = taskService.getAllTasks();
        return list.stream().map(taskMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/api/task/{id}")
    public TaskResponseDTO getTaskById(@PathVariable final Long id) throws TaskNotFoundException {
        var task = taskService.getTaskById(id);
        return taskMapper.toDTO(task);
    }

    @PostMapping("/api/task")
    public TaskResponseDTO addTask(@RequestBody @Valid final TaskRequestDTO taskRequestDTO) {
        var task = taskService.addTask(taskMapper.toEntity(taskRequestDTO));
        return taskMapper.toDTO(task);
    }

    @PutMapping("/api/task/{id}")
    public TaskResponseDTO updateTask(@PathVariable final Long id,
                                      @RequestBody @Valid final TaskRequestDTO taskRequestDTO)
            throws TaskNotFoundException {
        var task = taskService.updateTask(id, taskMapper.toEntity(taskRequestDTO));
        return taskMapper.toDTO(task);
    }

    @DeleteMapping("/api/task/{id}")
    public void deleteTask(@PathVariable final Long id) throws TaskNotFoundException {
        taskService.deleteTask(id);
    }
}
