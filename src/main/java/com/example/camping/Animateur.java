package com.example.camping;

import java.sql.PreparedStatement;
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
        ArrayList<Animateur> lesAnimateur = new ArrayList<>();
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try (Statement stmt = c.getConnection().createStatement(); ResultSet res = stmt.executeQuery(getQuery())) {

                while (res.next()) {
                    Animateur animateur = new Animateur(res.getInt("id_animateur"), res.getString("nom"), res.getString("prenom"), res.getString("email"));
                    lesAnimateur.add(animateur);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lesAnimateur;
    }

    public static void addAnimateur(String nom, String prenom, String email) {
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try (PreparedStatement stmt = c.getConnection().prepareStatement(getInsertQuery())) {
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setString(3, email);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getQuery() {
        return "SELECT * FROM animateur";
    }

    public static void deleteAnimateur(int idAnimateur) {
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try (PreparedStatement stmt = c.getConnection().prepareStatement("DELETE FROM animateur WHERE id_animateur = ?")) {
                stmt.setInt(1, idAnimateur);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateAnimateur(int idAnimateur, String text, String text1, String text2) {
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try (PreparedStatement stmt = c.getConnection().prepareStatement("UPDATE animateur SET nom = ?, prenom = ?, email = ? WHERE id_animateur = ?")) {
                stmt.setString(1, text);
                stmt.setString(2, text1);
                stmt.setString(3, text2);
                stmt.setInt(4, idAnimateur);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    private static String getInsertQuery() {
        return "INSERT INTO animateur (nom, prenom, email) VALUES (?, ?, ?)";
    }
}
