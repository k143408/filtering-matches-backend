package com.code.exercise.matches.service.impl;

import com.code.exercise.matches.converter.domain.UserConverter;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.MatchesDto;
import com.code.exercise.matches.repository.UserRepository;
import com.code.exercise.matches.service.UserLoaderService;
import com.code.exercise.matches.util.GenericConverter;
import com.code.exercise.matches.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Load Data from given JSON file to database.
 */
@RequiredArgsConstructor
@Service
public class UserLoaderServiceImpl implements UserLoaderService {

    private final UserRepository userRepository;
    private final GenericConverter genericConverter;
    private final UserConverter userConverter;

    @Value("classpath:data/matches.json")
    private Resource matchesJson;


    @Override
    @Transactional
    public void execute() {
        // fetch json as string from file.
        String content = IOUtils.asString(matchesJson);
        MatchesDto matchesDto = genericConverter.transformTo(content, MatchesDto.class);
        // convert to user objects.
        List<User> users = Optional.ofNullable(matchesDto.getMatches()).orElse(Collections.emptyList())
                .stream().map(userConverter::convert).collect(Collectors.toList());
        // Save
        userRepository.saveAll(users);
    }
}
