package com.example.camping;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;

import static com.example.camping.ConnexionBDD.initialiserConnexion;

public class DatabaseHelper {

    public static List<String> getAnimations() {
        List<String> animations = new ArrayList<>();
        try (Connection conn = initialiserConnexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT nom FROM animation")) {

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
        try (Connection conn = initialiserConnexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT nom, prenom FROM animateur")) {

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
        try (Connection conn = initialiserConnexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT libelle FROM lieu")) {

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
        try (Connection conn = initialiserConnexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_creneaux FROM creneaux ORDER BY id_creneaux ASC")) {

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
                // l'id de l'animation selon son nom
                ResultSet res = stmt.executeQuery("SELECT id FROM animation WHERE nom = '" + Animation + "'");
                res.next();
                int id_Animation = res.getInt("id");
                // l'id de l'animateur selon son nom
                res = stmt.executeQuery("SELECT id_animateur FROM animateur WHERE nom = '" + Animateur.split(" ")[0] + "' AND prenom = '" + Animateur.split(" ")[1] + "'");
                res.next();
                int id_Animateur = res.getInt("id_animateur");
                // l'id du lieu selon son nom
                res = stmt.executeQuery("SELECT id_lieu FROM lieu WHERE libelle = '" + Lieu + "'");
                res.next();
                int id_Lieu = res.getInt("id_lieu");

                // appel ma procedure stockée pour ajouter un créneau
                stmt.execute("CALL ajoutCreneau('" + date + "', " + id_Animation + ", " + id_Lieu + ", " + dure + ", " + id_Animateur + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean verifAjout(LocalDateTime dates, int i) {
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try {
                Statement stmt = c.getConnection().createStatement();
                ResultSet res = stmt.executeQuery("select checkAjout('" + dates + "', " + i + ")");
                res.next();
                return res.getBoolean(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static HashMap<Animateur, Creneaux> getAct(LocalDate currentDate) {
        HashMap<Animateur, Creneaux> lesAct = new HashMap<>();
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try {
                Statement stmt = c.getConnection().createStatement();
                ResultSet res = stmt.executeQuery(getQuery(currentDate));
                while (res.next()) {
                    Animateur _animateur = new Animateur(res.getInt("id_animateur"), res.getString("nom"), res.getString("prenom"), res.getString("email"));
                    Date date = res.getDate("date_heure");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Creneaux _creneaux = new Creneaux(res.getInt("id_creneaux"), cal, res.getInt("id"), res.getInt("id_lieu"));
                    lesAct.put(_animateur, _creneaux);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lesAct;
    }

    private static String getQuery(LocalDate currentDate) {
        LocalDate startOfWeek = currentDate.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = currentDate.with(java.time.DayOfWeek.SUNDAY);
        return "SELECT * FROM relation1 " + "INNER JOIN animateur ON animateur.id_animateur = relation1.id_animateur " + "INNER JOIN creneaux ON creneaux.id_creneaux = relation1.id_creneaux " + "INNER JOIN animation ON animation.id = creneaux.id " + "WHERE creneaux.date_heure BETWEEN '" + startOfWeek + "' AND '" + endOfWeek + "' " + "ORDER BY creneaux.date_heure ASC";
    }

    public static void addAnimateur(String nom, String prenom, String email) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("INSERT INTO animateur (nom, prenom, email) VALUES (?, ?, ?)")) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAnimateur(int id) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM animateur WHERE id_animateur = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAnimateur(int id, String nom, String prenom, String email) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("UPDATE animateur SET nom = ?, prenom = ?, email = ? WHERE id_animateur = ?")) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAnimation(String nom, String descriptif) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("INSERT INTO animation (nom, descriptif) VALUES (?, ?)")) {
            pstmt.setString(1, nom);
            pstmt.setString(2, descriptif);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAnimation(int id) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM animation WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAnimation(int id, String nom, String descriptif) {
        try (Connection conn = initialiserConnexion(); PreparedStatement pstmt = conn.prepareStatement("UPDATE animation SET nom = ?, descriptif = ? WHERE id = ?")) {
            pstmt.setString(1, nom);
            pstmt.setString(2, descriptif);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verifUser(String login, String password) {
        try (Connection conn = initialiserConnexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM compte WHERE login = '" + login + "' AND password = '" + password + "'")) {

            if (!rs.next()) {
                throw new CustomException("Login ou mot de passe incorrect", "Erreur d'authentification", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }
}
