package com.jspotify.database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            System.out.println(
                    "✅ PostgreSQL connection successful!"
            );

            System.out.println(
                    "Database: " +
                    connection.getCatalog()
            );

        } catch (Exception e) {

            System.out.println(
                    "❌ Database connection failed!"
            );

            e.printStackTrace();
        }
    }
}