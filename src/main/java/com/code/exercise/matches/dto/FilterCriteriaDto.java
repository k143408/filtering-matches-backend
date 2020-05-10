package com.code.exercise.matches.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilterCriteriaDto {
    private Boolean hasPhoto;
    private Boolean inContact;
    private Boolean favourite;
    private List<Float> compatibilityScore;
    private List<Integer> age;
    private List<Integer> height;
    @NotNull
    private Double distance;
    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
}
