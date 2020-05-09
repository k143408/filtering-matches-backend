package com.code.exercise.matches.controller;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.converter.dto.UserDtoConverter;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class UserControllerUnitTest extends BaseUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDtoConverter userDtoConverter;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        User dummyUser = getDummyUser();
        UserDto dummyUserDto =getDummyUserDto();
        when(userService.getRandomUser()).thenReturn(dummyUser);
        when(userDtoConverter.convert(dummyUser)).thenReturn(dummyUserDto);
        mockMvc.perform(get("/api/users/randomisation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(dummyUserDto))));
    }
}
