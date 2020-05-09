package com.code.exercise.matches.controller;

import com.code.exercise.matches.converter.dto.UserDtoConverter;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    /**
     * Fetch random user from database
     * @return User information
     */
    @GetMapping("randomisation")
    public UserDto randomUser() {
        return userDtoConverter.convert(userService.getRandomUser());
    }
}
