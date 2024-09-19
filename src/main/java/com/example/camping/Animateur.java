package com.example.camping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Animateur {
    private int id_Animateur;
    private String nom_Animateur;
    private String prenom_Animateur;
    private String email_Animateur;

    public Animateur(int id_Animateur, String nom_Animateur, String prenom_Animateur, String email_Animateur) {
        this.id_Animateur = id_Animateur;
        this.nom_Animateur = nom_Animateur;
        this.prenom_Animateur = prenom_Animateur;
        this.email_Animateur = email_Animateur;
    }

    public static ArrayList<Animateur> getAnimateur() {
        ConnexionBDD c = new ConnexionBDD();
        ArrayList<Animateur> lesAnimateur = new ArrayList<>();
        if (c != null) {
            try {
                String requete = "SELECT * FROM animateur";
                Statement stmt = c.getConnection().createStatement();
                ResultSet res = stmt.executeQuery(requete);

                while (res.next()) {
                    int _id = res.getInt("id_animateur");
                    String _nom = res.getString("nom");
                    String _prenom = res.getString("prenom");
                    String _email = res.getString("email");
                    Animateur e = new Animateur(_id, _nom, _prenom, _email);
                    lesAnimateur.add(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lesAnimateur;
    }

    public int getId_Animateur() {
        return id_Animateur;
    }

    public void setId_Animateur(int id_Animateur) {
        this.id_Animateur = id_Animateur;
    }

    public String getNom_Animateur() {
        return nom_Animateur;
    }

    public void setNom_Animateur(String nom_Animateur) {
        this.nom_Animateur = nom_Animateur;
    }

    public String getPrenom_Animateur() {
        return prenom_Animateur;
    }

    public void setPrenom_Animateur(String prenom_Animateur) {
        this.prenom_Animateur = prenom_Animateur;
    }

    public String getEmail_Animateur() {
        return email_Animateur;
    }

    public void setEmail_Animateur(String email_Animateur) {
        this.email_Animateur = email_Animateur;
    }

    @Override
    public String toString() {
        return id_Animateur + " - " + nom_Animateur + " - " + prenom_Animateur + " - " + email_Animateur;
    }
}
