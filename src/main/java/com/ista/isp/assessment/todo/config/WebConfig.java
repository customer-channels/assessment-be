package com.ista.isp.assessment.todo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class WebConfig {

    //    @Bean
    public ObjectMapper configureJson() {
        return new Jackson2ObjectMapperBuilder().indentOutput(true).build();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeJson()
    {
        return builder -> {
            builder.indentOutput(true);
        };
    }
}
