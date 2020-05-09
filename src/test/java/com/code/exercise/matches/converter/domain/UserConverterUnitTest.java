package com.code.exercise.matches.converter.domain;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.CityDto;
import com.code.exercise.matches.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserConverterUnitTest extends BaseUnitTest {

    @Autowired
    private UserConverter userConverter;

    @MockBean
    private CityConverter cityConverter;

    @Test
    public void shouldConvertUserDtoToUserObject() {
        UserDto dummyUserDto = getDummyUserDto();
        when(cityConverter.convert(any(CityDto.class))).thenReturn(City.builder().build());
        User user = userConverter.convert(dummyUserDto);
        Assertions.assertNotEquals(dummyUserDto.getCity().getName(), user.getCity().getName(), "Should not be equal");
        Assertions.assertEquals(user.getAge(), dummyUserDto.getAge(), "Age Should be equal");
    }
}
