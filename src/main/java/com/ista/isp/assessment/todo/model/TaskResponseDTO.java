package com.ista.isp.assessment.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskResponseDTO {

    private final Long id;
    private final String name;
    private final boolean checked;
    private final String description;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime deadline;

}
