package com.example.camping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.camping.ConnexionBDD.initialiserConnexion;

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

    public static List<String> getIdAnimation() {
        List<String> id = new ArrayList<>();
        try (Connection conn = initialiserConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_creneaux FROM creneaux ORDER BY id_creneaux ASC")) {

            while (rs.next()) {
                id.add(rs.getString("id_creneaux"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
