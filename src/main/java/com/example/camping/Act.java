package com.example.camping;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class Act {
    private Animateur animateur;
    private Creneaux creneaux;
    private Animation animation;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String horaires;

    public Act(Animateur animateur, Creneaux creneaux, Animation animation) {
        this.animateur = animateur;
        this.creneaux = creneaux;
        this.lundi = "";
        this.mardi = "";
        this.mercredi = "";
        this.jeudi = "";
        this.vendredi = "";
    }

    public Act() {
        this.horaires = "";
        this.lundi = "";
        this.mardi = "";
        this.mercredi = "";
        this.jeudi = "";
        this.vendredi = "";
    }


    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public Animateur getAnimateur() {
        return animateur;
    }

    public void setAnimateur(Animateur animateur) {
        this.animateur = animateur;
    }

    public Creneaux getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(Creneaux creneaux) {
        this.creneaux = creneaux;
    }

    public String getLundi() {
        return lundi;
    }

    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    @Override
    public String toString() {
        return creneaux.getLieu() + " - " + animateur.getNom_Animateur() + " " + animateur.getPrenom_Animateur();
    }


    public static HashMap<Animateur, Creneaux> getAct() {
        HashMap<Animateur, Creneaux> lesAct = new HashMap<>();
        ConnexionBDD c = new ConnexionBDD();
        if (c != null) {
            try {
                Statement stmt = c.getConnection().createStatement();
                ResultSet res = stmt.executeQuery(getQuery());
                while (res.next()) {
                    Animateur _animateur = new Animateur(res.getInt("id_animateur"), res.getString("nom"), res.getString("prenom"), res.getString("email"));
                    Date date = res.getDate("date_heure");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Creneaux _creneaux = new Creneaux(res.getInt("id_creneaux"), cal, res.getString("lieu"), res.getInt("duree"));
                    Animation _animation = new Animation(res.getInt("id"), res.getString("nom"), res.getString("descriptif"));
                    lesAct.put(_animateur, _creneaux);
                    System.out.println("Act found: " + _animateur + ", " + _creneaux + ", " + _animation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lesAct;
    }

    private static String getQuery() {
        return "SELECT * FROM relation1 " + "INNER JOIN animateur ON animateur.id_animateur = relation1.id_animateur " + "INNER JOIN creneaux ON creneaux.id_creneaux = relation1.id_creneaux " + "INNER JOIN animation ON animation.id = creneaux.id " + "ORDER BY creneaux.date_heure ASC";
    }

    private String getHoraires(Creneaux creneaux) {
        return creneaux.getStartTime() + "-" + creneaux.getEndTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return Objects.equals(animateur, act.animateur) &&
                Objects.equals(creneaux, act.creneaux) &&
                Objects.equals(animation, act.animation) &&
                Objects.equals(horaires, act.horaires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animateur, creneaux, animation, horaires);
    }


}
