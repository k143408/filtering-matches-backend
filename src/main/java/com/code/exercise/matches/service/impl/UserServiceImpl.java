package com.code.exercise.matches.service.impl;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.exception.ResourceNotFoundException;
import com.code.exercise.matches.repository.UserRepository;
import com.code.exercise.matches.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Fetch only one record from database via pagination
     *
     * @return User domain object.
     */
    @Override
    public User getRandomUser() {
        // Set only page for one record.
        return userRepository.randomUser(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }
}
