package com.example.camping;

import javafx.scene.control.Alert;

import java.sql.*;

public class ConnexionBDD {
    private static String driver = "mariadb";
    private static String database = "camping";
    private static String host = "localhost";
    private static String port = "3306";
    private static String username = "root";
    private static String password = "";

    public static Connection initialiserConnexion() {
        String URL = "jdbc:" + ConnexionBDD.driver + "://" + ConnexionBDD.host + ":" + ConnexionBDD.port + "/" + ConnexionBDD.database;
        try {
            return DriverManager.getConnection(URL, ConnexionBDD.username, ConnexionBDD.password);
        }
        catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText("Erreur de connexion à la base de données :" + ex.getMessage());
            a.showAndWait();
            return null;
        }
    }

    public static void fermerConnexion(Connection connexion) {
        try {
            connexion.close();
        }
        catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText("Erreur de fermeture de la connexion à la base de données :" + ex.getMessage());
            a.showAndWait();
        }
    }


    public Connection getConnection() {
        return initialiserConnexion();
    }
}
