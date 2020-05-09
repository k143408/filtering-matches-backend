package com.code.exercise.matches;

import com.code.exercise.matches.domain.City;
import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.CityDto;
import com.code.exercise.matches.dto.UserDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseUnitTest {

    @Value("classpath:json/user.json")
    protected Resource userJson;

    protected UserDto getDummyUserDto() {
        return UserDto
                .builder()
                .age(10)
                .compatibilityScore(0.3f)
                .city(CityDto.builder().name("Leads").lat(123.1231).lon(123.12).build())
                .displayName("Admin")
                .favourite(false)
                .heightInCm(123)
                .religion("Random")
                .jobTitle("Tester")
                .contactsExchanged(12)
                .mainPhoto("http://localhost")
                .build();
    }

    protected User getDummyUser() {
        return User
                .builder()
                .age(10)
                .compatibilityScore(0.3f)
                .city(City.builder().name("Leads").location(new Point(123.12,123.1231)).build())
                .name("Admin")
                .favourite(false)
                .heightInCm(123)
                .religion("Random")
                .jobTitle("Tester")
                .contactsExchanged(12)
                .mainPhoto("http://localhost")
                .build();
    }
}
