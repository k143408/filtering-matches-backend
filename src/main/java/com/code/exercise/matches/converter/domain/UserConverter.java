package com.code.exercise.matches.converter.domain;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<UserDto, User> {
    private final CityConverter cityConverter;

    /**
     * Convert the source object of type {@code UserDto} to target type {@code User}.
     *
     * @param userDto the {@link UserDto} object to convert, which must be an instance
     * @return User the {@link User} object, which must be an instance
     */
    @Override
    public User convert(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(u ->
                        User.builder()
                                .age(u.getAge())
                                .name(u.getDisplayName())
                                .jobTitle(u.getJobTitle())
                                .mainPhoto(u.getMainPhoto())
                                .favourite(u.getFavourite())
                                .heightInCm(u.getHeightInCm())
                                .contactsExchanged(u.getContactsExchanged())
                                .compatibilityScore(u.getCompatibilityScore())
                                // convert city object
                                .city(cityConverter.convert(u.getCity()))
                                .religion(u.getReligion())
                                .build())
                .orElseGet(() -> User.builder().build());
    }
}
