package com.example.camping;

import java.util.Calendar;

public class Creneaux {
    private int id_creneaux;
    private Calendar dateHeure;
    private String lieu;
    private int id;
    private int id_lieu;

    public Creneaux(int id_creneaux, Calendar date_heure, String lieu_Creneaux) {
        this.id_creneaux = id_creneaux;
        this.dateHeure = date_heure;
        this.lieu = lieu_Creneaux;
    }

    public int getId_creneaux() {
        return id_creneaux;
    }

    public void setId_creneaux(int id_creneaux) {
        this.id_creneaux = id_creneaux;
    }

    public Calendar getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Calendar dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

}
