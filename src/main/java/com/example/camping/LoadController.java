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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private TableView<Animateur> tableViewAnimateur;
    @FXML
    private TableView<Animation> tableViewAnimation;
    @FXML
    private TableView<Act> tableViewPlanning;
    @FXML
    private TableColumn<Animation, Integer> id_Animation;
    @FXML
    private TableColumn<Animation, String> nom_Animation;
    @FXML
    private TableColumn<Animation, String> descriptif_Animation;
    @FXML
    private TableColumn<Animateur, Integer> id_Animateur;
    @FXML
    private TableColumn<Animateur, String> nom_Animateur;
    @FXML
    private TableColumn<Animateur, String> prenom_Animateur;
    @FXML
    private TableColumn<Animateur, String> email_Animateur;
    @FXML
    private TextField txtNomAnimateur;
    @FXML
    private TextField txtPrenomAnimateur;
    @FXML
    private TextField txtEmailAnimateur;
    @FXML
    private Button btnAjoutAnimateur;
    @FXML
    private TextField txtNomAnimation;
    @FXML
    private TextArea txtDescriptifAnimation;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button button_prev_week;
    @FXML
    private Button button_next_week;
    @FXML
    private Button btnAjoutPlanning;
    @FXML
    private ChoiceBox<String> Animation_choiceBox;
    @FXML
    private ChoiceBox<String> Animateur_choiceBox;
    @FXML
    private ChoiceBox<String> Lieu_ChoiceBox;
    @FXML
    private ChoiceBox<String> id_Animation_choiceBox;
    @FXML
    private DatePicker date;
    @FXML
    private TextField HeurePl;
    @FXML
    private TextField dureeAct;

    private LocalDate currentDate;

    @FXML
    private void initialize() {
        currentDate = LocalDate.now();
        initializeTableViews();
        initializeButtons();
        initializeChoiceBoxes();
        initializeCalendar();
    }

    // Initialisation des TableView
    private void initializeTableViews() {
        actualisationTableViewAnimateur();
        actualisationTableViewAnimation();

        if (tableViewAnimateur != null) {
            configureTableColumnsAnimateur(tableViewAnimateur, id_Animateur, nom_Animateur, prenom_Animateur, email_Animateur);
            tableViewAnimateur.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtNomAnimateur.setText(newValue.getNom_Animateur());
                    txtPrenomAnimateur.setText(newValue.getPrenom_Animateur());
                    txtEmailAnimateur.setText(newValue.getEmail_Animateur());
                }
            });
        } else {
            System.out.println("tableViewAnimateur is null");
        }

        if (tableViewAnimation != null) {
            configureTableColumnsAnimations(tableViewAnimation, id_Animation, nom_Animation, descriptif_Animation);
            tableViewAnimation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtNomAnimation.setText(newValue.getNom_Animation());
                    txtDescriptifAnimation.setText(newValue.getDescriptif_Animation());
                }
            });
        } else {
            System.out.println("tableViewAnimation is null");
        }


    }

    // Initialisation des boutons
    private void initializeButtons() {
        if (btnAjoutAnimateur != null) {
            btnAjoutAnimateur.setOnAction(this::onAjoutAnimateurClicked);
        } else {
            System.out.println("btnAjoutAnimateur is null");
        }
        if (button_Anim != null) {
            button_Anim.setOnAction(this::onAnimateurButtonClick);
        } else {
            System.out.println("button_Anim is null");
        }

        if (button_prev_week != null) {
            button_prev_week.setOnAction(this::onPrevWeekClick);
        } else {
            System.out.println("button_prev_week is null");
        }

        if (button_next_week != null) {
            button_next_week.setOnAction(this::onNextWeekClick);
        } else {
            System.out.println("button_next_week is null");
        }

        if (btnAjoutPlanning != null) {
            btnAjoutPlanning.setOnAction(this::onAjoutPlanningClicked);
        } else {
            System.out.println("btnAjoutPlanning is null");
        }

        if (button_Acc != null) {
            button_Acc.setOnAction(this::onAccueilButtonClick);
        } else {
            System.out.println("button_ is null");
        }

    }

    // Initialisation des ChoiceBox
    private void initializeChoiceBoxes() {
        if (Animation_choiceBox != null && Animateur_choiceBox != null && Lieu_ChoiceBox != null) {
            ObservableList<String> animations = FXCollections.observableArrayList(DatabaseHelper.getAnimations());
            ObservableList<String> animateurs = FXCollections.observableArrayList(DatabaseHelper.getAnimateurs());
            ObservableList<String> lieux = FXCollections.observableArrayList(DatabaseHelper.getLieux());

            Animation_choiceBox.setItems(animations);
            Animateur_choiceBox.setItems(animateurs);
            Lieu_ChoiceBox.setItems(lieux);

            Animation_choiceBox.setOnAction(this::onAnimationSelected);
            Animateur_choiceBox.setOnAction(this::onAnimateurSelected);
            Lieu_ChoiceBox.setOnAction(this::onLieuSelected);

        } else {
            System.out.println("One or more ChoiceBoxes are null");
        }

        if (id_Animation_choiceBox != null) {
            ObservableList<String> id_Animation = FXCollections.observableArrayList(DatabaseHelper.getIdAnimation());

            id_Animation_choiceBox.setItems(id_Animation);

            id_Animation_choiceBox.setOnAction(this::onIdAnimationSelected);
        } else {
            System.out.println("tableViewPlanning is null");
        }
    }

    // Initialisation du calendrier
    private void initializeCalendar() {
        if (gridPane != null) {
            updateCalendar();
        } else {
            System.out.println("gridPane is null");
        }
    }

    // Gestion des événements des ChoiceBox
    @FXML
    private void onAnimationSelected(ActionEvent event) {
        String selectedAnimation = Animation_choiceBox.getValue();
        System.out.println("Selected Animation: " + selectedAnimation);
    }

    @FXML
    private void onAnimateurSelected(ActionEvent event) {
        String selectedAnimateur = Animateur_choiceBox.getValue();
        System.out.println("Selected Animateur: " + selectedAnimateur);
    }

    @FXML
    private void onLieuSelected(ActionEvent event) {
        String selectedLieu = Lieu_ChoiceBox.getValue();
        System.out.println("Selected Lieu: " + selectedLieu);
    }

    @FXML
    private void onIdAnimationSelected(ActionEvent event) {
        String selectedIdAnimation = id_Animation_choiceBox.getValue();
        System.out.println("Selected Id Animation: " + selectedIdAnimation);
    }

    // Mise à jour des TableView
    private void actualisationTableViewAnimateur() {
        try {
            ObservableList<Animateur> animateurs = FXCollections.observableArrayList(Animateur.getAnimateur());
            tableViewAnimateur.setItems(animateurs);
            configureTableColumnsAnimateur(tableViewAnimateur, id_Animateur, nom_Animateur, prenom_Animateur, email_Animateur);
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    private void actualisationTableViewAnimation() {
        try {
            ObservableList<Animation> animation = FXCollections.observableArrayList(Animation.getAnimation());
            tableViewAnimation.setItems(animation);
            configureTableColumnsAnimations(tableViewAnimation, id_Animation, nom_Animation, descriptif_Animation);
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    private void configureTableColumnsAnimations(TableView<Animation> tableViewAnimation, TableColumn<Animation, Integer> idAnimation, TableColumn<Animation, String> nomAnimation, TableColumn<Animation, String> descriptifAnimation) {
        idAnimation.setCellValueFactory(new PropertyValueFactory<>("id_Animation"));
        nomAnimation.setCellValueFactory(new PropertyValueFactory<>("nom_Animation"));
        descriptifAnimation.setCellValueFactory(new PropertyValueFactory<>("descriptif_Animation"));
    }

    // Gestion des événements des boutons
    @FXML
    private void onAjoutAnimateurClicked(ActionEvent event) {
        String nom = txtNomAnimateur.getText();
        String prenom = txtPrenomAnimateur.getText();
        String email = txtEmailAnimateur.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }

        try {
            Animateur.addAnimateur(nom, prenom, email);
            clearAnimateurFields();
            actualisationTableViewAnimateur();
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    public void onAjoutAnimationClicked(ActionEvent actionEvent) {
        String nom = txtNomAnimation.getText();
        String descriptif = txtDescriptifAnimation.getText();

        if (nom.isEmpty() || descriptif.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }

        try {
            Animation.addAnimation(nom, descriptif);
            clearAnimationFields();
            actualisationTableViewAnimation();
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    // Gestion des vues
    private void loadView(String fxmlFile, String title, Button currentButton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene;

            if ("Planning.fxml".equals(fxmlFile)) {
                scene = new Scene(root, 900, 450);
            } else {
                scene = new Scene(root, 1700, 900);
            }

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setFullScreen(false);
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);

            stage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    stage.setFullScreen(false);
                }
            });

            stage.setScene(scene);
            stage.show();

            // Close the current stage only if it's not Planning.fxml
            if (!"Planning.fxml".equals(fxmlFile)) {
                Stage currentStage = (Stage) currentButton.getScene().getWindow();
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActiviteButtonClick(ActionEvent actionEvent) {
        loadView("Activite.fxml", "Activite", button_Act);
    }

    public void onAnimateurButtonClick(ActionEvent actionEvent) {
        loadView("Animateur.fxml", "Animateur", button_Anim);
    }

    public void onAccueilButtonClick(ActionEvent actionEvent) {
        loadView("Accueil.fxml", "Accueil", button_Acc);
    }

    // Gestion des exceptions
    private void handleDatabaseException(Exception e) {
        if (e.getMessage().contains("Communications link failure")) {
            System.out.println("Erreur de connexion à la base de données");
        } else {
            System.out.println(e.getMessage());
        }
    }

    // Nettoyage des champs
    @FXML
    private void clearAnimateurFields() {
        txtNomAnimateur.clear();
        txtPrenomAnimateur.clear();
        txtEmailAnimateur.clear();
    }

    @FXML
    private void clearAnimationFields() {
        txtNomAnimation.clear();
        txtDescriptifAnimation.clear();
    }

    // Configuration des colonnes des TableView
    private void configureTableColumnsAnimateur(TableView<Animateur> tableView, TableColumn<Animateur, Integer> idColumn, TableColumn<Animateur, String> nomColumn, TableColumn<Animateur, String> prenomColumn, TableColumn<Animateur, String> emailColumn) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_Animateur"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_Animateur"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom_Animateur"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email_Animateur"));
    }

    // Gestion du calendrier
    private void updateCalendar() {
        gridPane.getChildren().clear();

        addLabelToGridPane("Lundi", 0, 1, "day");
        addLabelToGridPane("Mardi", 0, 2, "day");
        addLabelToGridPane("Mercredi", 0, 3, "day");
        addLabelToGridPane("Jeudi", 0, 4, "day");
        addLabelToGridPane("Vendredi", 0, 5, "day");

        for (int i = 1; i <= 11; i++) {
            addLabelToGridPane((i + 7) + "h", i, 0, "hour");
        }

        for (int i = 1; i <= 11; i++) {
            for (int j = 1; j <= 5; j++) {
                addLabelToGridPane("", i, j, "");
            }
        }

        HashMap<Animateur, Creneaux> act = Act.getAct(currentDate);
        for (Map.Entry<Animateur, Creneaux> entry : act.entrySet()) {
            Animateur animateur = entry.getKey();
            Creneaux creneaux = entry.getValue();
            Calendar datereel = creneaux.getDateHeure();
            LocalDateTime dateTime = creneaux.getDateHeure().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            LocalDate date = dateTime.toLocalDate();
            int dayOfWeek = date.getDayOfWeek().getValue();
            int hour = dateTime.getHour();
            int row = hour - 7;
            int col = dayOfWeek;

            addLabelToGridPane(animateur + "\n" + creneaux, row, col, "activity");
        }
    }

    private void addLabelToGridPane(String text, int row, int col, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        gridPane.add(label, col, row);
    }

    // Gestion des événements des boutons de navigation du calendrier
    @FXML
    private void onPrevWeekClick(ActionEvent event) {
        currentDate = currentDate.minusWeeks(1);
        updateCalendar();
    }

    @FXML
    private void onNextWeekClick(ActionEvent event) {
        currentDate = currentDate.plusWeeks(1);
        updateCalendar();
    }

    // Gestion des événements des boutons de suppression et de modification des animateurs
    public void onAjoutActClicked(ActionEvent actionEvent) {
        // Prendre les valeurs des choicebox, du datepicker et textfield
        String id_Animateur = Animateur_choiceBox.getValue();
        String id_Animation = Animation_choiceBox.getValue();
        String id_Lieu = Lieu_ChoiceBox.getValue();
        String dure = dureeAct.getText();
        String heure = HeurePl.getText();

        //gere les heures si c'est pas le bon format et le met au bon format et s'i on as les secondes les enleve si besoin et si on as 8 on rajoute 0 avant pour avoir 08 et pareil pour les autres nombres
        if (heure.length() == 1) {
            heure = "0" + heure + ":00:00";
        } else if (heure.length() == 2) {
            heure = heure + ":00:00";
        } else if (heure.length() == 4) {
            heure = "0" + heure;
        }   




        // Concatener la date et l'heure
        String date_heure = date.getValue() + " " + heure;


        // Transformation de date_heure en LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dates = LocalDateTime.parse(date_heure, formatter);

        System.out.println("id_Animateur: " + id_Animateur);
        System.out.println("id_Animation: " + id_Animation);
        System.out.println("id_Lieu: " + id_Lieu);
        System.out.println("date: " + dates);




        if (id_Animateur == null || id_Animation == null || id_Lieu == null || date == null || dure.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        } else {
            try {
                // Appel fonction ajoutPlanning dans la classe DatabaseHelper
                DatabaseHelper.ajoutPlanning(id_Animateur, id_Animation, id_Lieu, dates, dure);

                updateCalendar();
            } catch (Exception e) {
                handleDatabaseException(e);
            }
        }
    }

    public void onSupAnimateurClicked(ActionEvent actionEvent) {
        Animateur animateur = tableViewAnimateur.getSelectionModel().getSelectedItem();
        if (animateur != null) {
            try {
                Animateur.deleteAnimateur(animateur.getId_Animateur());
                actualisationTableViewAnimateur();
            } catch (Exception e) {
                handleDatabaseException(e);
            }
        }
    }

    public void onModifAnimateurClicked(ActionEvent actionEvent) {
        Animateur animateur = tableViewAnimateur.getSelectionModel().getSelectedItem();

        try {
            Animateur.updateAnimateur(animateur.getId_Animateur(), txtNomAnimateur.getText(), txtPrenomAnimateur.getText(), txtEmailAnimateur.getText());
            actualisationTableViewAnimateur();
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    public void onSupprimerAnimationClicked(ActionEvent actionEvent) {
        Animation animation = tableViewAnimation.getSelectionModel().getSelectedItem();
        if (animation != null) {
            try {
                Animation.deleteAnimation(animation.getId_Animation());
                actualisationTableViewAnimation();
            } catch (Exception e) {
                handleDatabaseException(e);
            }
        }
    }

    public void onModifAnimationClicked(ActionEvent actionEvent) {
        Animation animation = tableViewAnimation.getSelectionModel().getSelectedItem();

        try {
            Animation.updateAnimation(animation.getId_Animation(), txtNomAnimation.getText(), txtDescriptifAnimation.getText());
            actualisationTableViewAnimation();
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    public void onAjoutPlanningClicked(ActionEvent actionEvent) {
        loadView("Planning.fxml", "Planning", btnAjoutPlanning);
    }

    public void onSupprimerPlanningClicked(ActionEvent actionEvent) {

        String id = id_Animation_choiceBox.getValue();
        if (id == null || id.isEmpty()) {
            System.out.println("Veuillez sélectionner un créneau à supprimer.");
            return;
        }

        int idCreneaux = Integer.parseInt(id);

        ConnexionBDD c = new ConnexionBDD();
        Connection conn = c.getConnection();
        try {

            conn.setAutoCommit(false);

            String deleteRelationQuery = "DELETE FROM relation1 WHERE id_creneaux = ?";
            try (PreparedStatement pstmtRelation = conn.prepareStatement(deleteRelationQuery)) {
                pstmtRelation.setInt(1, idCreneaux);
                System.out.println("Executing query: " + pstmtRelation.toString());
                pstmtRelation.executeUpdate();
            }

            String deleteCreneauxQuery = "DELETE FROM creneaux WHERE id_creneaux = ?";
            try (PreparedStatement pstmtCreneaux = conn.prepareStatement(deleteCreneauxQuery)) {
                pstmtCreneaux.setInt(1, idCreneaux);
                System.out.println("Executing query: " + pstmtCreneaux.toString());
                pstmtCreneaux.executeUpdate();
            }

            conn.commit();
            System.out.println("Créneau supprimé avec succès.");

            updateCalendar();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
