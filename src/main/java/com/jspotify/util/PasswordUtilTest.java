package com.jspotify.util;

public class PasswordUtilTest {

    public static void main(String[] args) {

        System.out.println("========== PASSWORD TEST ==========");

        String password = "mypassword123";

        String hash =
                PasswordUtil.hashPassword(password);

        System.out.println("Original password:");
        System.out.println(password);

        System.out.println();

        System.out.println("Generated hash:");
        System.out.println(hash);

        System.out.println();

        boolean correctPassword =
                PasswordUtil.verifyPassword(
                        "mypassword123",
                        hash
                );

        boolean wrongPassword =
                PasswordUtil.verifyPassword(
                        "wrongpassword",
                        hash
                );

        System.out.println(
                "Correct password matches: "
                        + correctPassword
        );

        System.out.println(
                "Wrong password matches: "
                        + wrongPassword
        );
    }
}