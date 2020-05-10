package com.code.exercise.matches.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class City {

    private String name;
    @Column(columnDefinition = "geometry")
    private Point location;
}
