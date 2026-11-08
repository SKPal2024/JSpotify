package com.jspotify.service;

import com.jspotify.dao.UserDAO;
import com.jspotify.model.User;
import com.jspotify.model.UserRole;
import com.jspotify.util.PasswordUtil;

import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Registers a new user.
     */
    public User register(
            String username,
            String email,
            String password) throws SQLException {

        validateRegistrationInput(
                username,
                email,
                password
        );

        // Check whether username already exists
        User existingUsername =
                userDAO.findByUsername(username);

        if (existingUsername != null) {
            throw new IllegalArgumentException(
                    "Username already exists"
            );
        }

        // Check whether email already exists
        User existingEmail =
                userDAO.findByEmail(email);

        if (existingEmail != null) {
            throw new IllegalArgumentException(
                    "Email already exists"
            );
        }

        // Hash password before storing it
        String passwordHash =
                PasswordUtil.hashPassword(password);

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordHash);
        user.setProfileImage(null);
        user.setRole(UserRole.USER);

        return userDAO.create(user);
    }

    /**
     * Authenticates a user using username.
     */
    public User login(
            String username,
            String password) throws SQLException {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty"
            );
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty"
            );
        }

        User user =
                userDAO.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException(
                    "Invalid username or password"
            );
        }

        boolean passwordMatches =
                PasswordUtil.verifyPassword(
                        password,
                        user.getPasswordHash()
                );

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Invalid username or password"
            );
        }

        return user;
    }

    private void validateRegistrationInput(
            String username,
            String email,
            String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty"
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Email cannot be empty"
            );
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty"
            );
        }

        if (username.length() < 3) {
            throw new IllegalArgumentException(
                    "Username must contain at least 3 characters"
            );
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException(
                    "Password must contain at least 6 characters"
            );
        }
    }
}