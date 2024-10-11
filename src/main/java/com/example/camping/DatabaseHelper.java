package com.example.camping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.camping.ConnexionBDD.*;

public class DatabaseHelper {

    public static List<String> getAnimations() {
        List<String> animations = new ArrayList<>();
        try (Connection conn = initialiserConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nom FROM animation")) {

            while (rs.next()) {
                animations.add(rs.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animations;
    }

    public static List<String> getAnimateurs() {
        List<String> animateurs = new ArrayList<>();
        try (Connection conn = initialiserConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nom, prenom FROM animateur")) {

            while (rs.next()) {
                animateurs.add(rs.getString("nom") + " " + rs.getString("prenom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animateurs;
    }

    public static List<String> getLieux() {
        List<String> lieux = new ArrayList<>();
        try (Connection conn = initialiserConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT libelle FROM lieu")) {

            while (rs.next()) {
                lieux.add(rs.getString("libelle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lieux;
    }
    public static Integer getAnimation(String animation) {
        Integer id = null;
        try (Connection conn = initialiserConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM animation WHERE nom = ?")) {
            stmt.setString(1, animation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }



    public static String getLieuIdByName(String lieuName) {
        Integer lieuId = null;
        try (Connection conn = initialiserConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT id_lieu FROM lieu WHERE libelle = ?")) {
            stmt.setString(1, lieuName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    lieuId = rs.getInt("id_lieu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(lieuId);
    }

    public static Integer getAnimateurIdByName(String animateurName) {
        Integer animateurId = null;
        try (Connection conn = initialiserConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM animateur WHERE nom = ?")) {
            stmt.setString(1, animateurName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    animateurId = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animateurId;
    }


}
