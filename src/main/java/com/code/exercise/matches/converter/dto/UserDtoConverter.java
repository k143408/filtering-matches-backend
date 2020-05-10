package com.code.exercise.matches.converter.dto;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDtoConverter implements Converter<User, UserDto> {
    private final CityDtoConverter cityDtoConverter;

    /**
     * Convert the source object of type {@code User} to target type {@code UserDto}.
     *
     * @param user the {@link User} object to convert, which must be an instance
     * @return UserDto the {@link UserDto} object, which must be an instance
     */
    @Override
    public UserDto convert(User user) {
        return Optional.ofNullable(user)
                .map(u ->
                        UserDto.builder()
                                .age(u.getAge())
                                .displayName(u.getName())
                                .jobTitle(u.getJobTitle())
                                .mainPhoto(u.getMainPhoto())
                                .favourite(u.getFavourite())
                                .heightInCm(u.getHeightInCm())
                                .contactsExchanged(u.getContactsExchanged())
                                .compatibilityScore(u.getCompatibilityScore())
                                .city(cityDtoConverter.convert(u.getCity()))
                                .religion(u.getReligion())
                                .distance(Optional.ofNullable(u.getDistance()).map(d -> String.valueOf(d).concat(" km")).orElse(null))
                                .build())
                .orElseGet(() -> UserDto.builder().build());
    }
}
