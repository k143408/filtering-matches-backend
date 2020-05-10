package com.code.exercise.matches.converter.dto;

import com.code.exercise.matches.dto.FilterCriteria;
import com.code.exercise.matches.dto.FilterCriteriaDto;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFilterConverter implements Converter<FilterCriteriaDto, FilterCriteria> {
    private static Double valueForDistance = new Double(111.00);
    private GeometryFactory geometryFactory;

    @Override
    public FilterCriteria convert(FilterCriteriaDto source) {
        return FilterCriteria.builder()
                .hasPhoto(source.getHasPhoto())
                .inContact(source.getInContact())
                .favourite(source.getFavourite())
                .minCompatibilityScore(source.getCompatibilityScore().get(0))
                .maxCompatibilityScore(source.getCompatibilityScore().get(1))
                .minAge(source.getAge().get(0))
                .maxAge(source.getAge().get(1))
                .minHeight(source.getHeight().get(0))
                .distance(source.getDistance() / valueForDistance) // meter to km
                .maxHeight(source.getHeight().get(1))
                .distanceStatus(30 == source.getDistance())
                .centerPoint(geometryFactory.createPoint(new Coordinate(source.getLatitude(), source.getLongitude())))
                .build();
    }
}
