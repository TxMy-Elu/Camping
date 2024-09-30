package com.example.camping;

import java.util.Calendar;

public class Creneaux {
    private int id_creneaux;
    private Calendar date_heure;
    private String lieu_Creneaux;
    private int duree;

    public Creneaux(int id_creneaux, Calendar date_heure, String lieu_Creneaux, int duree) {
        this.id_creneaux = id_creneaux;
        this.date_heure = date_heure;
        this.lieu_Creneaux = lieu_Creneaux;
        this.duree = duree;
    }

    public int getId_creneaux() {
        return id_creneaux;
    }

    public void setId_creneaux(int id_creneaux) {
        this.id_creneaux = id_creneaux;
    }

    public Calendar getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Calendar date_heure) {
        this.date_heure = date_heure;
    }

    public String getLieu_Creneaux() {
        return lieu_Creneaux;
    }

    public void setLieu_Creneaux(String lieu_Creneaux) {
        this.lieu_Creneaux = lieu_Creneaux;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Calendar getDateHeure() {
        return date_heure;
    }
}
