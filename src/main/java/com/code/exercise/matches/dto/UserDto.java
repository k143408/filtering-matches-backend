package com.code.exercise.matches.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    public static UserDto EMPTY = UserDto.builder().build();
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("job_title")
    public String jobTitle;
    @JsonProperty("height_in_cm")
    public Integer heightInCm;
    @JsonProperty("main_photo")
    public String mainPhoto;
    @JsonProperty("compatibility_score")
    public Float compatibilityScore;
    @JsonProperty("contacts_exchanged")
    public Integer contactsExchanged;

    public Boolean favourite;
    public String religion;
    public Integer age;
    public CityDto city;
    public String distance;
}
