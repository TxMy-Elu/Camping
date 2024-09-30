package com.example.camping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    private TableView<Act> tableViewAccueil;
    @FXML
    private TableColumn<Animateur, Integer> id_Animateur;
    @FXML
    private TableColumn<Animateur, String> nom_Animateur;
    @FXML
    private TableColumn<Animateur, String> prenom_Animateur;
    @FXML
    private TableColumn<Animateur, String> email_Animateur;
    @FXML
    private TableColumn<Act, String> Lundi;
    @FXML
    private TableColumn<Act, String> Mardi;
    @FXML
    private TableColumn<Act, String> Mercredi;
    @FXML
    private TableColumn<Act, String> Jeudi;
    @FXML
    private TableColumn<Act, String> Vendredi;

    @FXML
    private TextField txtNomAnimateur;
    @FXML
    private TextField txtPrenomAnimateur;
    @FXML
    private TextField txtEmailAnimateur;
    @FXML
    private Button btnAjoutAnimateur;

    private LocalDate currentDate;


    @FXML
    private void initialize() {
        currentDate = LocalDate.now();
        actualisationTableViewAnimateur();
        if (btnAjoutAnimateur != null) {
            btnAjoutAnimateur.setOnAction(this::onAjoutAnimateurClicked);
        } else {
            System.out.println("btnAjoutAnimateur is null");
        }
        actualisationTableViewAccueil();
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

    private void onAjoutAnimateurClicked(ActionEvent event) {
        String nom = txtNomAnimateur.getText();
        String prenom = txtPrenomAnimateur.getText();
        String email = txtEmailAnimateur.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            // Afficher un message d'erreur si les champs ne sont pas remplis
            System.out.println("Veuillez remplir tous les champs.");
        } else {
            // Ajouter l'animateur à la base de données
            Animateur.addAnimateur(nom, prenom, email);
            // Actualiser la TableView
            actualisationTableViewAnimateur();
            // Vider les champs de texte
            txtNomAnimateur.clear();
            txtPrenomAnimateur.clear();
            txtEmailAnimateur.clear();
        }
    }

    private void actualisationTableViewAccueil() {
        try {
            HashMap<Animateur, Creneaux> actMap = Act.getAct();
            ObservableList<Act> accueils = FXCollections.observableArrayList();

            for (Map.Entry<Animateur, Creneaux> entry : actMap.entrySet()) {
                Act act = new Act(entry.getKey(), entry.getValue());
                Calendar cal = act.getCreneaux().getDateHeure();
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                // Vérifiez si la date est dans la semaine actuelle
                LocalDate actDate = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (actDate.isBefore(currentDate.plusDays(7)) && actDate.isAfter(currentDate.minusDays(1))) {
                    switch (dayOfWeek) {
                        case Calendar.MONDAY:
                            act.setLundi(act.toString());
                            break;
                        case Calendar.TUESDAY:
                            act.setMardi(act.toString());
                            break;
                        case Calendar.WEDNESDAY:
                            act.setMercredi(act.toString());
                            break;
                        case Calendar.THURSDAY:
                            act.setJeudi(act.toString());
                            break;
                        case Calendar.FRIDAY:
                            act.setVendredi(act.toString());
                            break;
                    }
                    accueils.add(act);
                }
            }

            tableViewAccueil.setItems(accueils);

            // Définir la cellFactory pour chaque colonne
            Lundi.setCellValueFactory(new PropertyValueFactory<>("lundi"));
            Lundi.setCellFactory(param -> new TableCell<Act, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                    } else {
                        Act act = getTableRow().getItem();
                        setText(act.getLundi());
                    }
                }
            });

            Mardi.setCellValueFactory(new PropertyValueFactory<>("mardi"));
            Mardi.setCellFactory(param -> new TableCell<Act, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                    } else {
                        Act act = getTableRow().getItem();
                        setText(act.getMardi());
                    }
                }
            });

            Mercredi.setCellValueFactory(new PropertyValueFactory<>("mercredi"));
            Mercredi.setCellFactory(param -> new TableCell<Act, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                    } else {
                        Act act = getTableRow().getItem();
                        setText(act.getMercredi());
                    }
                }
            });

            Jeudi.setCellValueFactory(new PropertyValueFactory<>("jeudi"));
            Jeudi.setCellFactory(param -> new TableCell<Act, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                    } else {
                        Act act = getTableRow().getItem();
                        setText(act.getJeudi());
                    }
                }
            });

            Vendredi.setCellValueFactory(new PropertyValueFactory<>("vendredi"));
            Vendredi.setCellFactory(param -> new TableCell<Act, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                    } else {
                        Act act = getTableRow().getItem();
                        setText(act.getVendredi());
                    }
                }
            });
        } catch (Exception e) {
            if (e.getMessage().contains("Communications link failure")) {
                System.out.println("Erreur de connexion à la base de données");
            } else {
                System.out.println("ok");
            }
        }
    }

    @FXML
    private void onPrevWeekClick(ActionEvent event) {
        currentDate = currentDate.minusWeeks(1);
        actualisationTableViewAccueil();
    }

    @FXML
    private void onNextWeekClick(ActionEvent event) {
        currentDate = currentDate.plusWeeks(1);
        actualisationTableViewAccueil();
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


}
