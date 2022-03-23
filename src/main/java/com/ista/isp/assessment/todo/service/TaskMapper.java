package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.model.TaskRequestDTO;
import com.ista.isp.assessment.todo.model.TaskResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponseDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskResponseDTO(
                task.getId(),
                task.getName(),
                task.isChecked(),
                task.getDescription(),
                task.getDeadline()
        );
    }

    public Task toEntity(TaskRequestDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        var task = new Task();
        task.setName(taskDTO.getName());
        task.setChecked(taskDTO.isChecked());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        return task;
    }
}
