package com.example.camping;

import java.util.Calendar;
import java.util.Date;

public class Creneaux {
    private int id_Creneaux;
    private Calendar DH_creneaux;
    private String lieu_Creneaux;
    private int duree_Creneaux;

    public Creneaux(int id_Creneaux, Calendar DH_creneaux, String lieu_Creneaux, int duree_Creneaux) {
        this.id_Creneaux = id_Creneaux;
        this.DH_creneaux = DH_creneaux;
        this.lieu_Creneaux = lieu_Creneaux;
        this.duree_Creneaux = duree_Creneaux;
    }

    public int getId_Creneaux() {
        return id_Creneaux;
    }

    public void setId_Creneaux(int id_Creneaux) {
        this.id_Creneaux = id_Creneaux;
    }

    public Calendar getDH_creneaux() {
        return DH_creneaux;
    }

    public void setDH_creneaux(Calendar DH_creneaux) {
        this.DH_creneaux = DH_creneaux;
    }

    public String getLieu_Creneaux() {
        return lieu_Creneaux;
    }

    public void setLieu_Creneaux(String lieu_Creneaux) {
        this.lieu_Creneaux = lieu_Creneaux;
    }

    public int getDuree_Creneaux() {
        return duree_Creneaux;
    }

    public void setDuree_Creneaux(int duree_Creneaux) {
        this.duree_Creneaux = duree_Creneaux;
    }


   
}
