package com.ista.isp.assessment.todo.cache;

import com.ista.isp.assessment.todo.dtos.TodoDto;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Getter
public class TodoCache {

    private static final Set<TodoDto> data;

    static {

        TodoDto todoRunning = TodoDto.builder()
                .id(1)
                .title("Make your daily training").build();
        TodoDto todoEnglish = TodoDto.builder()
                .id(2)
                .title("Improve my english").build();
        TodoDto todoDog = TodoDto.builder()
                .id(3)
                .title("Take the dog for a walk").build();

        data = Collections.synchronizedSet(new HashSet<>(
                Arrays.stream(new TodoDto[]{todoRunning, todoEnglish, todoDog}).collect(Collectors.toSet())));
    }

    private static final AtomicInteger sequence = new AtomicInteger(data.size()+1);

    public static int generate(){
        return sequence.getAndIncrement();
    }

    public Set<TodoDto> getData() {
        return data;
    }

}
