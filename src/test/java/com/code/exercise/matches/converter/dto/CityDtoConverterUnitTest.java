package com.code.exercise.matches.converter.dto;

import com.code.exercise.matches.BaseUnitTest;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.CityDto;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CityDtoConverterUnitTest extends BaseUnitTest {

    @Autowired
    private CityDtoConverter cityDtoConverter;

    @Test
    public void shouldConvertCityDtoToCity() {
        User dummyUserDto = getDummyUser();
        CityDto city = cityDtoConverter.convert(dummyUserDto.getCity());
        assertNotNull(city, "City object should not be null");
        assertEquals(dummyUserDto.getCity().getLocation().getX(), city.getLon(), "Location X should be equal");
        assertEquals(dummyUserDto.getCity().getLocation().getY(), city.getLat(), "Location Y should be equal");
    }
}
