package com.jspotify.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class SceneManager {

    private SceneManager() {
        // Prevent object creation
    }

    public static void switchScene(
            Stage stage,
            String fxmlFile,
            String title
    ) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        SceneManager.class
                                .getResource(
                                        "/fxml/" + fxmlFile
                                )
                );

        Parent root = loader.load();

        Scene scene =
                new Scene(
                        root,
                        500,
                        600
                );

        stage.setTitle(title);

        stage.setScene(scene);

        stage.show();
    }
}