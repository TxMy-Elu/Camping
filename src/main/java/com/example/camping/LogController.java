package com.example.camping;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogController {
    @FXML
    private TextField logintxt;
    @FXML
    private TextField passwordtxt;
    @FXML
    private TextField Nom_Animateur;
    @FXML
    private TextField Prenom_Animateur;
    @FXML
    private TextField Email_Animateur;
    @FXML
    private Button button_confirmed;

    @FXML
    protected void onConnexionButtonClick() {
        String login = logintxt.getText();
        String password = passwordtxt.getText();

        System.out.println("Login: " + login);
        System.out.println("Password: " + password);

        if (valide(login, password)) {
            loadView("Accueil.fxml", "Accueil", logintxt);
        } else {
            System.out.println("Erreur de connexion");
        }
    }

    private void loadView(String fxmlFile, String title, TextField currentButton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

            Stage stage = new Stage();
            stage.setTitle(title);
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

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) currentButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean valide(String login, String password) {
        String user = "";
        String mdp = "";
        return user.equals(login) && mdp.equals(password);
    }
}
