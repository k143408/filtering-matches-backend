package com.code.exercise.matches.util;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.exception.InvalidJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

class GenericConverterUnitTest extends BaseUnitTest {

    @Autowired
    private GenericConverter genericConverter;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
    public void shouldTransformToGivenTargetClass() throws JsonProcessingException {
        when(objectMapper.readValue(anyString(), eq(UserDto.class))).thenReturn(UserDto.EMPTY);
        UserDto userDto = genericConverter.transformTo("{}", UserDto.class);
        Assertions.assertNotNull(userDto);
        verify(objectMapper, times(1)).readValue(anyString(), eq(UserDto.class));
    }

    @Test
    public void shouldThrowInvalidJsonException() {
        Assertions.assertThrows(InvalidJsonException.class, () -> {
            when(objectMapper.readValue(anyString(), eq(UserDto.class))).thenThrow(new InvalidJsonException("Invalid"));
            genericConverter.transformTo("{}", UserDto.class);
        });
    }
}
