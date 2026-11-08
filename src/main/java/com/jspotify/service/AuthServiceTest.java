package com.jspotify.service;

import com.jspotify.model.User;
import com.jspotify.session.UserSession;

public class AuthServiceTest {

    public static void main(String[] args) {

        AuthService authService =
                new AuthService();

        long timestamp =
                System.currentTimeMillis();

        String username =
                "authuser_" + timestamp;

        String email =
                "auth_" + timestamp + "@example.com";

        String password =
                "mypassword123";

        try {

            System.out.println(
                    "========== REGISTER =========="
            );

            User registeredUser =
                    authService.register(
                            username,
                            email,
                            password
                    );

            System.out.println(
                    "Registered user:"
            );

            System.out.println(
                    registeredUser
            );

            System.out.println();

            System.out.println(
                    "========== LOGIN =========="
            );

            User loggedInUser =
                    authService.login(
                            username,
                            password
                    );

            System.out.println(
                    "Login successful!"
            );

            System.out.println(
                    loggedInUser
            );

            System.out.println();

            System.out.println(
                    "========== SESSION =========="
            );

            UserSession.login(
                    loggedInUser
            );

            System.out.println(
                    "Logged in: "
                            + UserSession.isLoggedIn()
            );

            System.out.println(
                    "Current user: "
                            + UserSession
                            .getCurrentUser()
                            .getUsername()
            );

            System.out.println(
                    "Is admin: "
                            + UserSession.isAdmin()
            );

            System.out.println();

            System.out.println(
                    "========== LOGOUT =========="
            );

            UserSession.logout();

            System.out.println(
                    "Logged in: "
                            + UserSession.isLoggedIn()
            );

            System.out.println(
                    "Current user: "
                            + UserSession.getCurrentUser()
            );

            System.out.println();

            System.out.println(
                    "✅ AUTHENTICATION TEST PASSED!"
            );

        } catch (Exception e) {

            System.out.println();

            System.out.println(
                    "❌ AUTHENTICATION TEST FAILED!"
            );

            e.printStackTrace();
        }
    }
}