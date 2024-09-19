package com.example.camping;

public class Lieu {
    private int id_lieu;
    private String libelle;

    public Lieu(int id_lieu, String libelle) {
        this.id_lieu = id_lieu;
        this.libelle = libelle;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
