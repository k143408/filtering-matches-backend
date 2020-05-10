package com.code.exercise.matches.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilterCriteria {
    private Boolean hasPhoto;
    private Boolean inContact;
    private Boolean favourite;
    private Float minCompatibilityScore;
    private Float maxCompatibilityScore;
    private Integer minAge;
    private Integer maxAge;
    private Integer minHeight;
    private Integer maxHeight;
    private Double distance;
    private Boolean distanceStatus;
    private Geometry centerPoint;

}
