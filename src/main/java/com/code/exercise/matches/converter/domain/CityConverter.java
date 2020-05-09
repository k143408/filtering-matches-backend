package com.code.exercise.matches.converter.domain;

import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.dto.CityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CityConverter implements Converter<CityDto, City> {

    /**
     * Convert the source object of type {@code CityDto} to target type {@code City}.
     *
     * @param cityDto the {@link CityDto} object to convert, which must be an instance
     * @return City the {@link City} object, which must be an instance
     */
    @Override
    public City convert(CityDto cityDto) {
        return Optional.ofNullable(cityDto)
                .map(c ->
                        City.builder()
                                .name(c.getName())
                                .location(new Point(c.getLon(),c.getLat() ))
                                .build())
                .orElseGet(() -> City.builder().build());
    }
}
