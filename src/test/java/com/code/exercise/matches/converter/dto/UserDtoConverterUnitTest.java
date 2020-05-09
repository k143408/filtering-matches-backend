package com.code.exercise.matches.converter.dto;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.CityDto;
import com.code.exercise.matches.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserDtoConverterUnitTest extends BaseUnitTest {

    @Autowired
    private UserDtoConverter userDtoConverter;

    @MockBean
    private CityDtoConverter cityDtoConverter;

    @Test
    public void shouldConvertUserToUserDto(){
        User dummyUser = getDummyUser();
        when(cityDtoConverter.convert(any(City.class))).thenReturn(CityDto.builder().build());
        UserDto userDto = userDtoConverter.convert(dummyUser);
        assertNotNull(userDto, "UserDto Should not be null");
        Assertions.assertNotEquals(dummyUser.getCity().getName(), userDto.getCity().getName(), "Should not be equal");
        Assertions.assertEquals(dummyUser.getAge(), userDto.getAge(), "Age Should be equal");
    }

}
