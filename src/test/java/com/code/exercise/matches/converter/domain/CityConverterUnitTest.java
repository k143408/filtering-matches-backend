package com.code.exercise.matches.converter.domain;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CityConverterUnitTest extends BaseUnitTest {

    @Autowired
    private CityConverter cityConverter;

    @Test
    public void shouldConvertCityDtoToCity(){
        UserDto dummyUserDto = getDummyUserDto();
        City city = cityConverter.convert(dummyUserDto.getCity());
        assertNotNull(city, "City object should not be null");
        assertEquals(city.getLocation().getX(), dummyUserDto.getCity().getLon(), "Location X should be equal");
        assertEquals(city.getLocation().getY(), dummyUserDto.getCity().getLat(), "Location Y should be equal");
    }
}
