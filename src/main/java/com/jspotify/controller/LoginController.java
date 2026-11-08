package com.jspotify.controller;

import com.jspotify.model.User;
import com.jspotify.service.AuthService;
import com.jspotify.session.UserSession;
import com.jspotify.util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Hyperlink registerLink;

    private final AuthService authService;


    public LoginController() {

        this.authService =
                new AuthService();
    }


    @FXML
    private void handleLogin() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                passwordField
                        .getText();


        try {

            /*
             * Authenticate the user.
             */
            User user =
                    authService.login(
                            username,
                            password
                    );


            /*
             * Store user in session.
             */
            UserSession.login(user);


            System.out.println(
                    "Login successful."
            );

            System.out.println(
                    "User: "
                            + user.getUsername()
            );


            /*
             * Get current window.
             */
            Stage stage =
                    (Stage) usernameField
                            .getScene()
                            .getWindow();


            /*
             * Move to Dashboard.
             */
            SceneManager.switchScene(
                    stage,
                    "Dashboard.fxml",
                    "JSpotify"
            );


        } catch (Exception e) {

            messageLabel.setText(
                    e.getMessage()
            );
        }
    }


    @FXML
    private void handleRegister() {

        try {

            Stage stage =
                    (Stage) registerLink
                            .getScene()
                            .getWindow();


            SceneManager.switchScene(
                    stage,
                    "Register.fxml",
                    "JSpotify - Register"
            );


        } catch (Exception e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Could not open registration page."
            );
        }
    }
}