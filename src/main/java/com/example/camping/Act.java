package com.example.camping;

import java.time.LocalDate;
import java.util.HashMap;

public class Act {
    private Animateur animateur;
    private Creneaux creneaux;
    private Animation animation;

    public Act(Animateur animateur, Creneaux creneaux) {
        this.animateur = animateur;
        this.creneaux = creneaux;
    }

    public Animateur getAnimateur() {
        return animateur;
    }

    public void setAnimateur(Animateur animateur) {
        this.animateur = animateur;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Creneaux getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(Creneaux creneaux) {
        this.creneaux = creneaux;
    }

    public String getId_DateHeure() {
        return creneaux.getDateHeure().toString();
    }

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
