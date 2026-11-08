package com.jspotify.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DatabaseConfigTest {

    @Test
    void configurationShouldProvideSafeDefaults() {
        assertDoesNotThrow(() -> {
            String url = DatabaseConfig.URL;
            String user = DatabaseConfig.USER;
            String password = DatabaseConfig.PASSWORD;

            assert url != null;
            assert user != null;
            assert password != null;
        });
    }
}
