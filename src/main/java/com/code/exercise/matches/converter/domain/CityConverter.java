package com.code.exercise.matches.converter.domain;

import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.dto.CityDto;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CityConverter implements Converter<CityDto, City> {

    private GeometryFactory geometryFactory;

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
                                .location(geometryFactory.createPoint(new Coordinate(c.getLat(), c.getLon())))
                                .build())
                .orElseGet(() -> City.builder().build());
    }
}
