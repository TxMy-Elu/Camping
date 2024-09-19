package com.example.camping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadController {
    @FXML
    private Button button_Act;
    @FXML
    private Button button_Anim;
    @FXML
    private Button button_Plan;
    @FXML
    private Button button_Acc;
    @FXML
    private Button button_ajout_animateur;
    @FXML
    private TableView<Animateur> tableViewAnimateur;
    @FXML
    private TableView<Animation> tableViewAnimation;
    @FXML
    private TableColumn<Animateur, Integer> id_Animateur;
    @FXML
    private TableColumn<Animateur, String> nom_Animateur;
    @FXML
    private TableColumn<Animateur, String> prenom_Animateur;
    @FXML
    private TableColumn<Animateur, String> email_Animateur;
    @FXML
    private TableColumn<Animation, Integer> id_Animation;
    @FXML
    private TableColumn<Animation, String> nom_Animation;
    @FXML
    private TableColumn<Animation, String> descriptif_Animation;



    @FXML
    private void initialize() {


        actualisationTableViewAnimateur();
        actualisationTableViewAnimation();

    }

    private void actualisationTableViewAnimateur() {
        try {
            ObservableList<Animateur> animateurs = FXCollections.observableArrayList(Animateur.getAnimateur());
            tableViewAnimateur.setItems(animateurs);
            id_Animateur.setCellValueFactory(new PropertyValueFactory<>("id_Animateur"));
            nom_Animateur.setCellValueFactory(new PropertyValueFactory<>("nom_Animateur"));
            prenom_Animateur.setCellValueFactory(new PropertyValueFactory<>("prenom_Animateur"));
            email_Animateur.setCellValueFactory(new PropertyValueFactory<>("email_Animateur"));
        } catch (Exception e) {
            if (e.getMessage().contains("Communications link failure")) {
                System.out.println("Erreur de connexion à la base de données");
            } else {
                System.out.println("ok");
            }
        }
    }
    private void actualisationTableViewAnimation() {
        try {
            ObservableList<Animation> animation = FXCollections.observableArrayList(Animation.getAnimation());
            tableViewAnimation.setItems(animation);
            id_Animation.setCellValueFactory(new PropertyValueFactory<>("id_Animation"));
            nom_Animation.setCellValueFactory(new PropertyValueFactory<>("nom_Animation"));
            descriptif_Animation.setCellValueFactory(new PropertyValueFactory<>("descriptif_Animation"));
        } catch (Exception e) {
            if (e.getMessage().contains("Communications link failure")) {
                System.out.println("Erreur de connexion à la base de données");
            } else {
                System.out.println("ok");
            }
        }
    }


    private void loadPlanning() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Planning.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

            Stage accueilStage = new Stage();
            accueilStage.setTitle("Planning");
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
            Stage currentStage = (Stage) button_Plan.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadActivite() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Activite.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

            Stage accueilStage = new Stage();
            accueilStage.setTitle("Activite");
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
            Stage currentStage = (Stage) button_Act.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAnimateur() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Animateur.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

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
            Stage currentStage = (Stage) button_Anim.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccueil_P() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

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
            Stage currentStage = (Stage) button_Acc.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAjoutAnim() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutAnim.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

            Stage accueilStage = new Stage();
            accueilStage.setTitle("AjoutAnim");
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
            Stage currentStage = (Stage) button_ajout_animateur.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActiviteButtonClick(ActionEvent actionEvent) {
        loadActivite();
    }

    public void onAnimateurButtonClick(ActionEvent actionEvent) {
        loadAnimateur();
    }

    public void onPlanningButtonClick(ActionEvent actionEvent) {
        loadPlanning();
    }

    public void onAccueilButtonClick(ActionEvent actionEvent) {
        loadAccueil_P();
    }

    public void onAjoutAnimateurClick(ActionEvent actionEvent) {
        loadAjoutAnim();
    }
}
