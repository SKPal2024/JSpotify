package com.jspotify.database;

import com.jspotify.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
        // Prevent object creation
    }

    public static Connection getConnection() throws SQLException {
        if (DatabaseConfig.URL == null || DatabaseConfig.URL.isBlank()) {
            throw new SQLException("Missing database URL. Set -Djspotify.db.url=... or JSPOTIFY_DB_URL");
        }

        if (DatabaseConfig.USER == null || DatabaseConfig.USER.isBlank()) {
            throw new SQLException("Missing database user. Set -Djspotify.db.user=... or JSPOTIFY_DB_USER");
        }

        if (DatabaseConfig.PASSWORD == null || DatabaseConfig.PASSWORD.isBlank()) {
            throw new SQLException("Missing database password. Set -Djspotify.db.password=... or JSPOTIFY_DB_PASSWORD");
        }

        return DriverManager.getConnection(
                DatabaseConfig.URL,
                DatabaseConfig.USER,
                DatabaseConfig.PASSWORD
        );
    }
}