package com.example.camping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
    private TableColumn<Act, String> Horaires;
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

    ConnexionBDD c = new ConnexionBDD();

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
        Set<Act> actSet = new HashSet<>();

        // Créer une liste d'horaires fixes
        List<String> horaires = Horaire.getHoraires();
        System.out.println("Horaires: " + horaires); // Ajouter une instruction de débogage

        // Créer une map pour stocker les activités par horaire
        Map<String, Act> actByHoraire = new HashMap<>();

        for (Map.Entry<Animateur, Creneaux> entry : actMap.entrySet()) {
            Animateur animateur = entry.getKey();
            Creneaux creneaux = entry.getValue();
            Animation animation = getAnimationForAct(animateur, creneaux);

            if (animation != null) {
                for (String horaire : horaires) {
                    Act act = actByHoraire.getOrDefault(horaire, new Act(animateur, creneaux, animation));
                    System.out.println("Act: " + act); // Ajouter une instruction de débogage
                    act.setHoraires(horaire);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(creneaux.getDateHeure().getTime());
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                    LocalDate actDate = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (actDate.isBefore(currentDate.plusDays(7)) && actDate.isAfter(currentDate.minusDays(1))) {
                        setActDay(act, dayOfWeek);
                        act.setHoraires(getHoraires(creneaux)); // Définir les horaires
                        actByHoraire.put(act.getHoraires(), act);

                        if (actSet.add(act)) {
                            accueils.add(act);
                        }
                    }


                }
            } else {
                System.out.println("No animation found for act: " + animateur + ", " + creneaux);
            }
        }

        // Limiter à 10 lignes
        FilteredList<Act> filteredAccueils = new FilteredList<>(accueils, act -> true);
        filteredAccueils.setPredicate(act -> filteredAccueils.indexOf(act) < 10);

        tableViewAccueil.setItems(filteredAccueils);
        setTableCellFactories();

        // Ajouter des instructions de débogage
        System.out.println("TableView items count: " + tableViewAccueil.getItems().size());
        for (Act act : tableViewAccueil.getItems()) {
            System.out.println("Act in TableView: " + act);
        }
    } catch (Exception e) {
        handleDatabaseException(e);
    }
}



    private Animation getAnimationForAct(Animateur animateur, Creneaux creneaux) {

        Animation animation = null;
        if (c != null) {
            try (PreparedStatement stmt = c.getConnection().prepareStatement(getAnimationQuery())) {
                stmt.setInt(1, animateur.getId_Animateur());
                stmt.setInt(2, creneaux.getId_creneaux());
                ResultSet res = stmt.executeQuery();
                if (res.next()) {
                    animation = new Animation(res.getInt("id"), res.getString("nom"), res.getString("descriptif"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animation;
    }

    private String getAnimationQuery() {
        return "SELECT animation.id, animation.nom, animation.descriptif " + "FROM animation " + "INNER JOIN creneaux ON creneaux.id = animation.id " + "INNER JOIN relation1 ON relation1.id_creneaux = creneaux.id_creneaux " + "WHERE relation1.id_animateur = ? AND relation1.id_creneaux = ?";
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
        setTableCellFactory(Horaires, "horaires");
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
                setStyle("");
            } else {
                Act act = getTableRow().getItem();
                String value = null;
                switch (property) {
                    case "horaires":
                        value = act.getHoraires();
                        break;
                    case "lundi":
                        value = act.getLundi();
                        break;
                    case "mardi":
                        value = act.getMardi();
                        break;
                    case "mercredi":
                        value = act.getMercredi();
                        break;
                    case "jeudi":
                        value = act.getJeudi();
                        break;
                    case "vendredi":
                        value = act.getVendredi();
                        break;
                }
                if (value == null || value.isEmpty()) {
                    setText(" ");
                } else {
                    setText(value);
                }
            }
        }
    });
}


    private String getHoraires(Creneaux creneaux) {
        return creneaux.getStartTime() + "-" + creneaux.getEndTime();
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
            System.out.println(e.getMessage());
        }
    }

    private void clearAnimateurFields() {
        txtNomAnimateur.clear();
        txtPrenomAnimateur.clear();
        txtEmailAnimateur.clear();
    }
}
