package com.ista.isp.assessment.todo.dto;

import lombok.*;

@Data
@Builder
public class TaskRsDto {

	private Integer id;
	private String taskDescription;
	private Boolean checked;
}
