package com.example.camping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Animation {
    private int id_Animation;
    private String nom_Animation;
    private String descriptif_Animation;

    public Animation(int id_Animation, String nom_Animation, String descriptif_Animation) {
        this.id_Animation = id_Animation;
        this.nom_Animation = nom_Animation;
        this.descriptif_Animation = descriptif_Animation;
    }

    public static ArrayList<Animation> getAnimation() {
        ArrayList<Animation> lesAnimation = new ArrayList<>();
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try (Statement stmt = c.getConnection().createStatement(); ResultSet res = stmt.executeQuery(getQuery())) {

                while (res.next()) {
                    Animation animation = new Animation(res.getInt("id"), res.getString("nom"), res.getString("descriptif"));
                    lesAnimation.add(animation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lesAnimation;
    }

    private static String getQuery() {
        return "SELECT * FROM animation";
    }

    public int getId_Animation() {
        return id_Animation;
    }

    public void setId_Animation(int id_Animation) {
        this.id_Animation = id_Animation;
    }

    public String getNom_Animation() {
        return nom_Animation;
    }

    public void setNom_Animation(String nom_Animation) {
        this.nom_Animation = nom_Animation;
    }

    public String getDescriptif_Animation() {
        return descriptif_Animation;
    }

    public void setDescriptif_Animation(String descriptif_Animation) {
        this.descriptif_Animation = descriptif_Animation;
    }

    @Override
    public String toString() {
        return id_Animation + " - " + nom_Animation + " - " + descriptif_Animation;
    }
}
