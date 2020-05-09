package com.code.exercise.matches.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CityDto {
    private String name;
    private Double lat;
    private Double lon;
}
