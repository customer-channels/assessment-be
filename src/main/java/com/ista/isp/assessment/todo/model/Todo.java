package com.ista.isp.assessment.todo.model;
import lombok.*;
import javax.validation.constraints.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Todo {

    private Integer id;

    @NotEmpty(message = "Please provide a title")
    private String title;
}


