package com.jspotify.config;

public final class DatabaseConfig {

    private DatabaseConfig() {
        // Prevent object creation
    }

    private static String configValue(String propertyName, String envName, String defaultValue) {
        String value = System.getenv(envName);
        if (value != null && !value.isBlank()) {
            return value.trim();
        }

        value = System.getProperty(propertyName);
        if (value != null && !value.isBlank()) {
            return value.trim();
        }

        return defaultValue == null ? null : defaultValue.trim();
    }

    public static final String URL =
            configValue("jspotify.db.url", "JSPOTIFY_DB_URL",
                    "jdbc:postgresql://localhost:5432/jspotify");

    public static final String USER =
            configValue("jspotify.db.user", "JSPOTIFY_DB_USER",
                    "postgres");

    public static final String PASSWORD =
            configValue("jspotify.db.password", "JSPOTIFY_DB_PASSWORD",
                    "");
}