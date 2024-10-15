package com.example.camping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public static void ajoutPlanning(String Animateur, String Animation, String Lieu, LocalDateTime date, String dure) {
       ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try {
                Statement stmt = c.getConnection().createStatement();
                // l'id de l'animation celon son nom
                ResultSet res = stmt.executeQuery("SELECT id FROM animation WHERE nom = '" + Animation + "'");
                res.next();
                int id_Animation = res.getInt("id");
                // l'id de l'animateur celon son nom
                res = stmt.executeQuery("SELECT id_animateur FROM animateur WHERE nom = '" + Animateur.split(" ")[0] + "' AND prenom = '" + Animateur.split(" ")[1] + "'");
                res.next();
                int id_Animateur = res.getInt("id_animateur");
                // l'id du lieu celon son nom
                res = stmt.executeQuery("SELECT id_lieu FROM lieu WHERE libelle = '" + Lieu + "'");
                res.next();
                int id_Lieu = res.getInt("id_lieu");

                //appel ma procedure stockee pour ajouter un creneau
                stmt.execute("CALL ajoutCreneau('" + date + "', " + id_Animation + ", " + id_Lieu + ", " + dure + ", " + id_Animateur + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
