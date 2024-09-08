package com.example.camping;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log.fxml"));
        //load th style sheet

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Log");
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}