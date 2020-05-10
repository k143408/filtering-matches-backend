package com.code.exercise.matches.it;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.FilterCriteriaDto;
import com.code.exercise.matches.dto.UserDto;
import com.code.exercise.matches.repository.UserRepository;
import com.code.exercise.matches.util.IOUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Value("classpath:json/user-filter.json")
    private Resource userFilterJson;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldUserDtoNotNull() {
        UserDto body = getRandomUserForApi();
        Assertions.assertNotNull(body, "Should not null");
    }

    @Test
    public void shouldGetFilterFromRandomUser() throws JsonProcessingException {
        User kate = userRepository.findByName("Kate");
        FilterCriteriaDto filterCriteriaDto = objectMapper.readValue(IOUtils.asString(userFilterJson), FilterCriteriaDto.class);
        filterCriteriaDto.setLatitude(kate.getCity().getLocation().getY());
        filterCriteriaDto.setLongitude(kate.getCity().getLocation().getX());

        List<UserDto> filterList = this.restTemplate.exchange(
                new DefaultUriBuilderFactory().builder().path("/api/users").query(getQueryParams(filterCriteriaDto)).build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {
                }).getBody();

        Assertions.assertTrue(filterList.size() == 6);
    }

    /**
     * private methods
     */
    private String getQueryParams(FilterCriteriaDto filterCriteriaDto) {
        Map<String, Object> queryMap = objectMapper.convertValue(filterCriteriaDto, Map.class);
        return doQueryProcess(queryMap);
    }


    private UserDto getRandomUserForApi() {
        return this.restTemplate.getForObject("/api/users/randomisation", UserDto.class);
    }


    private String doQueryProcess(Map<String, Object> queryMap) {
        StringBuilder sb = new StringBuilder();
        queryMap.entrySet().forEach(
                e -> {
                    if (e.getValue() instanceof List) {
                        for (Object v : (List) e.getValue()) {
                            toQuery(sb, e.getKey(), v.toString());
                        }
                    } else toQuery(sb, e.getKey(), e.getValue().toString());

                });
        return sb.toString();
    }

    private void toQuery(StringBuilder sb, String key, String value) {
        sb.append(key);
        sb.append("=");
        sb.append(value);
        sb.append("&");
    }

}
