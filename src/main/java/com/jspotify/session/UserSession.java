package com.jspotify.session;

import com.jspotify.model.User;
import com.jspotify.model.UserRole;

public final class UserSession {

    private static User currentUser;

    private UserSession() {
        // Prevent object creation
    }

    public static void login(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User cannot be null"
            );
        }

        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return isLoggedIn()
                && currentUser.getRole() == UserRole.ADMIN;
    }
}