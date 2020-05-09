package com.code.exercise.matches.util;


import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

/**
 * Utility functions of I/O.
 */
public class IOUtils {

    private IOUtils() {
    }

    /**
     * Get content  from Resource file
     *
     * @param resource Resource file
     * @return content of file.
     */
    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), Charset.defaultCharset())) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
