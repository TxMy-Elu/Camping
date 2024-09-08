package com.example.camping;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField logintxt;

    @FXML
    private TextField passwordtxt;

    @FXML
    protected void onConnexionButtonClick() {
        String login = logintxt.getText();
        String password = passwordtxt.getText();

        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
    }
}