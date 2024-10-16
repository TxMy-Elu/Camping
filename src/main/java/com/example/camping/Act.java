package com.example.camping;

import java.time.LocalDate;
import java.util.HashMap;

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

    public Act(Animateur animateur, Creneaux creneaux) {
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

    // Add getter for id_DateHeure
    public String getId_DateHeure() {
        return creneaux.getDateHeure().toString();
    }

    // Add getter for nom_Animation
    public String getNom_Animation() {
        return animation.getNom_Animation();
    }

    @Override
    public String toString() {
        return animateur + "\n" + creneaux + "\n" + animation;
    }

    public static HashMap<Animateur, Creneaux> getAct(LocalDate currentDate) {
        return DatabaseHelper.getAct(currentDate);
    }
}
