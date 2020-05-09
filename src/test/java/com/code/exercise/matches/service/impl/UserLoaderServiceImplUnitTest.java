package com.code.exercise.matches.service.impl;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.converter.domain.UserConverter;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.MatchesDto;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.repository.UserRepository;
import com.code.exercise.matches.util.GenericConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class UserLoaderServiceImplUnitTest extends BaseUnitTest {

    @Autowired
    private UserLoaderServiceImpl userLoaderService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private GenericConverter genericConverter;
    @MockBean
    private UserConverter userConverter;

    @Test
    void shouldLoadData() {
        when(userRepository.saveAll(anyList())).thenReturn(Collections.emptyList());
        when(userConverter.convert(any(UserDto.class))).thenReturn(User.builder().build());
        when(genericConverter.transformTo(anyString(), eq(MatchesDto.class))).thenReturn(MatchesDto.EMPTY);
        userLoaderService.execute();
        verify(genericConverter, times(1)).transformTo(anyString(), eq(MatchesDto.class));
        verify(userRepository, times(1)).saveAll(anyList());
        // Because of empty result
        verify(userConverter, times(0)).convert(any(UserDto.class));
    }
}
