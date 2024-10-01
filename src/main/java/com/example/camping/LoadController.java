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
            handleDatabaseException(e);
        }
    }

    private void onAjoutAnimateurClicked(ActionEvent event) {
        String nom = txtNomAnimateur.getText();
        String prenom = txtPrenomAnimateur.getText();
        String email = txtEmailAnimateur.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs.");
        } else {
            Animateur.addAnimateur(nom, prenom, email);
            actualisationTableViewAnimateur();
            clearAnimateurFields();
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

                LocalDate actDate = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (actDate.isBefore(currentDate.plusDays(7)) && actDate.isAfter(currentDate.minusDays(1))) {
                    setActDay(act, dayOfWeek);
                    accueils.add(act);
                }
            }

            tableViewAccueil.setItems(accueils);
            setTableCellFactories();
        } catch (Exception e) {
            handleDatabaseException(e);
        }
    }

    private void setActDay(Act act, int dayOfWeek) {
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
    }

    private void setTableCellFactories() {
        setTableCellFactory(Lundi, "lundi");
        setTableCellFactory(Mardi, "mardi");
        setTableCellFactory(Mercredi, "mercredi");
        setTableCellFactory(Jeudi, "jeudi");
        setTableCellFactory(Vendredi, "vendredi");
    }

    private void setTableCellFactory(TableColumn<Act, String> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(param -> new TableCell<Act, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Act act = getTableRow().getItem();
                    switch (property) {
                        case "lundi":
                            setText(act.getLundi());
                            break;
                        case "mardi":
                            setText(act.getMardi());
                            break;
                        case "mercredi":
                            setText(act.getMercredi());
                            break;
                        case "jeudi":
                            setText(act.getJeudi());
                            break;
                        case "vendredi":
                            setText(act.getVendredi());
                            break;
                    }
                }
            }
        });
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
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
    }

    private void clearAnimateurFields() {
        txtNomAnimateur.clear();
        txtPrenomAnimateur.clear();
        txtEmailAnimateur.clear();
    }
}