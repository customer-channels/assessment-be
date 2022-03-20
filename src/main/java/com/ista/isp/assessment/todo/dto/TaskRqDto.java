package com.ista.isp.assessment.todo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class TaskRqDto {

	@NotNull(message = "Task Description cannot be null.")
	private String taskDescription;
	@NotNull(message = "Checked value cannot be null.")
	private Boolean checked;
}
