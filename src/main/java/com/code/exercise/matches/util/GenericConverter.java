package com.code.exercise.matches.util;

import com.code.exercise.matches.exception.InvalidJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class GenericConverter {

    private final ObjectMapper objectMapper;

    /**
     * Method to deserialize JSON content from given JSON content String.
     *
     * @param content String
     * @param target  - deserialize target class
     * @param <T>     - Target class
     */
    public <T> T transformTo(String content, Class<T> target) {
        try {
            return objectMapper.readValue(content, target);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse json to {}", target.getName());
            throw new InvalidJsonException("Unable to parse from given json");
        }
    }
}
