package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.model.TaskRequestDTO;
import com.ista.isp.assessment.todo.service.TaskMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    public void toEntityTest() {

        var taskMapper = new TaskMapper();
        var now = LocalDateTime.now();
        var taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setName("task1");
        taskRequestDTO.setChecked(true);
        taskRequestDTO.setDescription("description1");
        taskRequestDTO.setDeadline(now);

        var taskEntity = taskMapper.toEntity(taskRequestDTO);
        assertEquals(taskEntity.getName(), taskRequestDTO.getName());
        assertEquals(taskEntity.getDescription(), taskRequestDTO.getDescription());
        assertEquals(taskEntity.isChecked(), taskRequestDTO.isChecked());
        assertEquals(taskEntity.getDeadline(), taskRequestDTO.getDeadline());

    }
}
