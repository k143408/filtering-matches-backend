package com.code.exercise.matches.service.impl;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.exception.ResourceNotFoundException;
import com.code.exercise.matches.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class UserServiceImplUnitTest extends BaseUnitTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldGetUserFromUserRepository() {
        when(userRepository.randomUser(PageRequest.of(0, 1))).thenReturn(Collections.singletonList(User.builder().build()));
        User randomUser = userServiceImpl.getRandomUser();
        Assertions.assertNotNull(randomUser);
        verify(userRepository, times(1)).randomUser(PageRequest.of(0, 1));
    }

    @Test
    void shouldGetResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            when(userRepository.randomUser(PageRequest.of(0, 1))).thenReturn(Collections.emptyList());
            userServiceImpl.getRandomUser();
        });

    }
}
