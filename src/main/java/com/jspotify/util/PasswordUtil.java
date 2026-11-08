package com.jspotify.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {

    private PasswordUtil() {
        // Prevent object creation
    }

    /**
     * Hashes a plain-text password using BCrypt.
     *
     * @param plainPassword the user's plain-text password
     * @return BCrypt password hash
     */
    public static String hashPassword(String plainPassword) {

        if (plainPassword == null || plainPassword.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty"
            );
        }

        return BCrypt.hashpw(
                plainPassword,
                BCrypt.gensalt(12)
        );
    }

    /**
     * Verifies a plain-text password against a BCrypt hash.
     *
     * @param plainPassword the password entered by the user
     * @param hashedPassword the password hash stored in database
     * @return true if password matches
     */
    public static boolean verifyPassword(
            String plainPassword,
            String hashedPassword) {

        if (plainPassword == null || hashedPassword == null) {
            return false;
        }

        return BCrypt.checkpw(
                plainPassword,
                hashedPassword
        );
    }
}