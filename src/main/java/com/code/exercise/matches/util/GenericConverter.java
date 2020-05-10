package com.code.exercise.matches.util;

import com.code.exercise.matches.exception.InvalidJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

    /**
     * Convenience method for doing two-step conversion from given value, into
     * instance of given value type
     *
     * @param source String
     * @param target - deserialize target class
     * @param <T>    - Target class
     */
    public <T> T transformTo(Object source, Class<T> target) {
        try {
            return objectMapper.convertValue(source, target);
        } catch (IllegalArgumentException e) {
            log.error("Unable to parse object to {}", target.getName());
            throw new InvalidJsonException("Unable to covert to target");
        }
    }

    /**
     * Convenience method for doing two-step conversion from given value, into
     * instance of given value type
     *
     * @param source String
     * @param target - deserialize target class
     * @param <T>    - Target class
     */
    public <T> T transformTo(Object source, TypeReference<T> target) {
        try {
            return objectMapper.convertValue(source, target);
        } catch (IllegalArgumentException e) {
            log.error("Unable to parse object to {}", target);
            throw new InvalidJsonException("Unable to covert to target");
        }
    }

}
