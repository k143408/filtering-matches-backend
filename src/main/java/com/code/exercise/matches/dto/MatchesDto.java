package com.code.exercise.matches.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatchesDto {
    public static MatchesDto EMPTY = MatchesDto.builder().build();
    List<UserDto> matches;
}
