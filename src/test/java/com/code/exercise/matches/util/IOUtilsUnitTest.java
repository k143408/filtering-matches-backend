package com.code.exercise.matches.util;

import com.code.exercise.matches.BaseUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.UncheckedIOException;


public class IOUtilsUnitTest extends BaseUnitTest {


    @Test
    public void shouldGetContentAsStringFromResource() {
        String content = IOUtils.asString(userJson);
        Assertions.assertNotNull(content);
    }

    @Test
    public void shouldThrowUncheckedIOException() {
        Assertions.assertThrows(UncheckedIOException.class, () ->
                IOUtils.asString(new ClassPathResource("empty"))
        );
    }
}
