package com.example.camping;

import javafx.event.ActionEvent;
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
            loadAccueil();
        } else {
            System.out.println("Erreur de connexion");
        }
    }

    @FXML
    protected void onbutton_confirmedClick() {
        String name = Nom_Animateur.getText();
        String prenom = Prenom_Animateur.getText();
        String email = Email_Animateur.getText();

        System.out.println("Login: " + name);
        System.out.println("Password: " + prenom);
        System.out.println("Password: " + email);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Animateur.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1200, 700);

            Stage accueilStage = new Stage();
            accueilStage.setTitle("Animateur");
            accueilStage.setFullScreen(false);
            accueilStage.setResizable(false); // Empêche le redimensionnement de la fenêtre
            accueilStage.initStyle(StageStyle.DECORATED); // Utilise le style de fenêtre par défaut

            // Empêcher les utilisateurs de passer en plein écran via les contrôles de la fenêtre
            accueilStage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    accueilStage.setFullScreen(false);
                }
            });

            accueilStage.setScene(scene);
            accueilStage.show();

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) button_confirmed.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadAccueil() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1200, 700);

            Stage accueilStage = new Stage();
            accueilStage.setTitle("Accueil");
            accueilStage.setFullScreen(false);
            accueilStage.setResizable(false); // Empêche le redimensionnement de la fenêtre
            accueilStage.initStyle(StageStyle.DECORATED); // Utilise le style de fenêtre par défaut

            // Empêcher les utilisateurs de passer en plein écran via les contrôles de la fenêtre
            accueilStage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    accueilStage.setFullScreen(false);
                }
            });

            accueilStage.setScene(scene);
            accueilStage.show();

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) logintxt.getScene().getWindow();
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