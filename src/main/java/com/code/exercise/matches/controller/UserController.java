package com.code.exercise.matches.controller;

import com.code.exercise.matches.converter.dto.UserDtoConverter;
import com.code.exercise.matches.dto.FilterCriteriaDto;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    /**
     * Fetch random user from database
     *
     * @return User information
     */
    @GetMapping("randomisation")
    public UserDto randomUser() {
        return userDtoConverter.convert(userService.getRandomUser());
    }


    @GetMapping
    public List<UserDto> userFilterCriteria(@Valid @NonNull FilterCriteriaDto filterCriteriaDto) {
        return userService.getListByFilterCriteria(filterCriteriaDto).stream()
                .map(userDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
