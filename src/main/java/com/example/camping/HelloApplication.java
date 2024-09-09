package com.example.camping;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log.fxml"));
        //load th style sheet

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Log");

        stage.setFullScreen(false);
        stage.setResizable(false); // Empêche le redimensionnement de la fenêtre
        stage.initStyle(StageStyle.DECORATED); // Utilise le style de fenêtre par défaut

        // Empêcher les utilisateurs de passer en plein écran via les contrôles de la fenêtre
        stage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.setFullScreen(false);
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}

