package com.jspotify.dao;

import com.jspotify.database.DatabaseConnection;
import com.jspotify.model.User;
import com.jspotify.model.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    // =========================================================
    // CREATE
    // =========================================================

    public User create(User user) throws SQLException {

        String sql = """
                INSERT INTO users
                (username, email, password_hash, profile_image, role)
                VALUES (?, ?, ?, ?, ?::user_role)
                RETURNING id, created_at
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUsername());

            statement.setString(2, user.getEmail());

            statement.setString(3, user.getPasswordHash());

            statement.setString(4, user.getProfileImage());

            statement.setString(5, user.getRole().name());


            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    user.setId(
                            resultSet.getLong("id")
                    );

                    user.setCreatedAt(
                            resultSet.getTimestamp("created_at")
                                    .toLocalDateTime()
                    );

                    return user;
                }
            }
        }

        throw new SQLException(
                "Failed to create user."
        );
    }


    // =========================================================
    // READ BY ID
    // =========================================================

    public User findById(long id) throws SQLException {

        String sql = """
                SELECT *
                FROM users
                WHERE id = ?
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);


            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToUser(resultSet);
                }
            }
        }

        return null;
    }


    // =========================================================
    // READ BY USERNAME
    // =========================================================

    public User findByUsername(
            String username
    ) throws SQLException {

        String sql = """
                SELECT *
                FROM users
                WHERE username = ?
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);


            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToUser(resultSet);
                }
            }
        }

        return null;
    }


    // =========================================================
    // READ BY EMAIL
    // =========================================================

    public User findByEmail(
            String email
    ) throws SQLException {

        String sql = """
                SELECT *
                FROM users
                WHERE email = ?
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);


            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToUser(resultSet);
                }
            }
        }

        return null;
    }


    // =========================================================
    // READ ALL
    // =========================================================

    public List<User> findAll() throws SQLException {

        String sql = """
                SELECT *
                FROM users
                ORDER BY id
                """;


        List<User> users = new ArrayList<>();


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                users.add(
                        mapResultSetToUser(resultSet)
                );
            }
        }

        return users;
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public boolean update(User user)
            throws SQLException {

        String sql = """
                UPDATE users
                SET username = ?,
                    email = ?,
                    password_hash = ?,
                    profile_image = ?,
                    role = ?::user_role
                WHERE id = ?
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getEmail()
            );

            statement.setString(
                    3,
                    user.getPasswordHash()
            );

            statement.setString(
                    4,
                    user.getProfileImage()
            );

            statement.setString(
                    5,
                    user.getRole().name()
            );

            statement.setLong(
                    6,
                    user.getId()
            );


            int rowsAffected =
                    statement.executeUpdate();


            return rowsAffected > 0;
        }
    }


    // =========================================================
    // DELETE
    // =========================================================

    public boolean delete(long id)
            throws SQLException {

        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);


            int rowsAffected =
                    statement.executeUpdate();


            return rowsAffected > 0;
        }
    }


    // =========================================================
    // RESULT SET → USER OBJECT
    // =========================================================

    private User mapResultSetToUser(
            ResultSet resultSet
    ) throws SQLException {

        User user = new User();


        user.setId(
                resultSet.getLong("id")
        );


        user.setUsername(
                resultSet.getString("username")
        );


        user.setEmail(
                resultSet.getString("email")
        );


        user.setPasswordHash(
                resultSet.getString("password_hash")
        );


        user.setProfileImage(
                resultSet.getString("profile_image")
        );


        user.setRole(
                UserRole.valueOf(
                        resultSet.getString("role")
                )
        );


        Timestamp timestamp =
                resultSet.getTimestamp("created_at");


        if (timestamp != null) {

            user.setCreatedAt(
                    timestamp.toLocalDateTime()
            );
        }


        return user;
    }
}