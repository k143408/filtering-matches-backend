package com.code.exercise.matches.converter.dto;

import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.dto.CityDto;
import org.locationtech.jts.geom.Point;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CityDtoConverter implements Converter<City, CityDto> {

    /**
     * Convert the source object of type {@code City} to target type {@code CityDto}.
     *
     * @param city the {@link City} object to convert, which must be an instance
     * @return CityDto the {@link CityDto} object, which must be an instance
     */
    @Override
    public CityDto convert(City city) {
        return Optional.ofNullable(city)
                .map(c ->
                        CityDto.builder()
                                .name(c.getName())
                                .lat(Optional.ofNullable(c.getLocation()).map(Point::getY).orElse(null))
                                .lon(Optional.ofNullable(c.getLocation()).map(Point::getX).orElse(null))
                                .build())
                .orElseGet(() -> CityDto.builder().build());
    }
}
