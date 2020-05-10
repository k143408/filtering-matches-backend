package com.code.exercise.matches.service;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.FilterCriteriaDto;

import java.util.List;

public interface UserService {
    User getRandomUser();

    List<User> getListByFilterCriteria(FilterCriteriaDto filterCriteriaDto);
}
