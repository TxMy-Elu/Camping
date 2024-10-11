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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private TextField txtNomAnimateur;
    @FXML
    private TextField txtPrenomAnimateur;
    @FXML
    private TextField txtEmailAnimateur;
    @FXML
    private Button btnAjoutAnimateur;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button button_prev_week;
    @FXML
    private Button button_next_week;
    @FXML
    private ChoiceBox<String> Animation_choiceBox;
    @FXML
    private ChoiceBox<String> Animateur_choiceBox;
    @FXML
    private ChoiceBox<String> Lieu_ChoiceBox;

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

        if (gridPane != null) {
            updateCalendar();
        } else {
            System.out.println("gridPane is null");
        }

        // Initialiser les ChoiceBox
        if (Animation_choiceBox != null && Animateur_choiceBox != null && Lieu_ChoiceBox != null) {
            initializeChoiceBoxes();
        } else {
            System.out.println("One or more ChoiceBoxes are null");
        }

        if (tableViewAnimateur != null) {
            tableViewAnimateur.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    txtNomAnimateur.setText(newSelection.getNom_Animateur());
                    txtPrenomAnimateur.setText(newSelection.getPrenom_Animateur());
                    txtEmailAnimateur.setText(newSelection.getEmail_Animateur());
                }
            });
        } else {
            System.out.println("tableViewAnimateur is null");
        }
    }

    private void initializeChoiceBoxes() {
        // Remplir les ChoiceBox avec des données réelles
        ObservableList<String> animations = FXCollections.observableArrayList(DatabaseHelper.getAnimations());
        ObservableList<String> animateurs = FXCollections.observableArrayList(DatabaseHelper.getAnimateurs());
        ObservableList<String> lieux = FXCollections.observableArrayList(DatabaseHelper.getLieux());

        Animation_choiceBox.setItems(animations);
        Animateur_choiceBox.setItems(animateurs);
        Lieu_ChoiceBox.setItems(lieux);

        // Associer les gestionnaires d'événements
        Animation_choiceBox.setOnAction(this::onAnimationSelected);
        Animateur_choiceBox.setOnAction(this::onAnimateurSelected);
        Lieu_ChoiceBox.setOnAction(this::onLieuSelected);
    }

    @FXML
    private void onAnimationSelected(ActionEvent event) {
        String selectedAnimation = Animation_choiceBox.getValue();
        System.out.println("Selected Animation: " + selectedAnimation);
        // Ajoutez ici le code pour gérer la sélection de l'animation
    }

    @FXML
    private void onAnimateurSelected(ActionEvent event) {
        String selectedAnimateur = Animateur_choiceBox.getValue();
        System.out.println("Selected Animateur: " + selectedAnimateur);
        // Ajoutez ici le code pour gérer la sélection de l'animateur
    }

    @FXML
    private void onLieuSelected(ActionEvent event) {
        String selectedLieu = Lieu_ChoiceBox.getValue();
        System.out.println("Selected Lieu: " + selectedLieu);
        // Ajoutez ici le code pour gérer la sélection du lieu
    }


    private void actualisationTableViewAnimateur() {
        try {
            ObservableList<Animateur> animateurs = FXCollections.observableArrayList(Animateur.getAnimateur());
            tableViewAnimateur.setItems(animateurs);
            configureTableColumns(tableViewAnimateur, id_Animateur, nom_Animateur, prenom_Animateur, email_Animateur);
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

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

    private void loadView(String fxmlFile, String title, Button currentButton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1700, 900);

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

            Stage currentStage = (Stage) currentButton.getScene().getWindow();
            currentStage.close();
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

    public void onPlanningButtonClick(ActionEvent actionEvent) {
        loadView("Planning.fxml", "Planning", button_Plan);
    }

    public void onAccueilButtonClick(ActionEvent actionEvent) {
        loadView("Accueil.fxml", "Accueil", button_Acc);
    }

    private void handleDatabaseException(Exception e) {
        if (e.getMessage().contains("Communications link failure")) {
            System.out.println("Erreur de connexion à la base de données");
        } else {
            System.out.println(e.getMessage());
        }
    }

    private void clearAnimateurFields() {
        txtNomAnimateur.clear();
        txtPrenomAnimateur.clear();
        txtEmailAnimateur.clear();
    }

    private void configureTableColumns(TableView<Animateur> tableView, TableColumn<Animateur, Integer> idColumn, TableColumn<Animateur, String> nomColumn, TableColumn<Animateur, String> prenomColumn, TableColumn<Animateur, String> emailColumn) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_Animateur"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_Animateur"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom_Animateur"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email_Animateur"));
    }

    private void updateCalendar() {
        gridPane.getChildren().clear();

        // Ajouter les jours de la semaine
        addLabelToGridPane("Lundi", 0, 1, "day");
        addLabelToGridPane("Mardi", 0, 2, "day");
        addLabelToGridPane("Mercredi", 0, 3, "day");
        addLabelToGridPane("Jeudi", 0, 4, "day");
        addLabelToGridPane("Vendredi", 0, 5, "day");

        // Ajouter les heures
        for (int i = 1; i <= 11; i++) {
            addLabelToGridPane((i + 7) + "h", i, 0, "hour");
        }

        // Ajouter les cellules pour chaque heure de chaque jour
        for (int i = 1; i <= 11; i++) {
            for (int j = 1; j <= 5; j++) {
                addLabelToGridPane("", i, j, "");
            }
        }

        // Charger les activités pour la semaine en cours
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

            System.out.println("Adding activity: " + animateur + ", " + creneaux + ",  J : " + dayOfWeek + "  h : " + hour + ", " + row + ", " + col + "date reel : " + datereel);
            addLabelToGridPane(animateur.getNom_Animateur() + /*activite en queston*/
                    " - " + creneaux.getLieu(), row, col, "activity");
        }
    }


    private void addLabelToGridPane(String text, int row, int col, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        gridPane.add(label, col, row);
    }

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

    public void OnAjoutAct(ActionEvent actionEvent) {
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
}
