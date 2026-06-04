package com.nicogabriel.ariee.core.internal.util;

import com.nicogabriel.ariee.core.exception.InternalException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Resources {

    private static final String PROPERTIES_FILE_PATH = "/com/nicogabriel/ariee/core/ariee-core.properties";

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = Resources.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            if (inputStream == null) {
                throw new InternalException("Could not find resource: '%s'.".formatted(PROPERTIES_FILE_PATH));
            }

            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new InternalException("Could not read resource: '%s'.".formatted(PROPERTIES_FILE_PATH), exception);
        }
    }

    private Resources() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (Strings.isNullOrBlank(value)) {
            throw new InternalException("Missing internal property: '%s'.".formatted(key));
        }

        return value;
    }

    public static String getPropertyOrDefault(String key, String defaultValue) {
        String value = PROPERTIES.getProperty(key);

        if (Strings.isNullOrBlank(value)) {
            return defaultValue;
        }

        return value;
    }
}
