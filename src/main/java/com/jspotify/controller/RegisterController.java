package com.jspotify.controller;

import com.jspotify.model.User;
import com.jspotify.service.AuthService;
import com.jspotify.util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    private final AuthService authService;

    public RegisterController() {
        this.authService = new AuthService();
    }

    @FXML
    private void handleRegister() {

        String username =
                usernameField.getText().trim();

        String email =
                emailField.getText().trim();

        String password =
                passwordField.getText();

        String confirmPassword =
                confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {

            messageLabel.setText(
                    "Passwords do not match."
            );

            return;
        }

        try {

            User user =
                    authService.register(
                            username,
                            email,
                            password
                    );

            messageLabel.setText(
                    "Registration successful! " +
                    "You can now log in."
            );

            System.out.println(
                    "New user registered: "
                            + user.getUsername()
            );

            usernameField.clear();
            emailField.clear();
            passwordField.clear();
            confirmPasswordField.clear();

        } catch (Exception e) {

            messageLabel.setText(
                    e.getMessage()
            );
        }
    }

    @FXML
    private void handleLogin() {

        try {

            Stage stage =
                    (Stage) messageLabel
                            .getScene()
                            .getWindow();

            SceneManager.switchScene(
                    stage,
                    "Login.fxml",
                    "JSpotify - Login"
            );

        } catch (Exception e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Could not open login page."
            );
        }
    }
}