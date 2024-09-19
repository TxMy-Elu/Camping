package com.example.camping;

public class Animateur {
    private int id_Animateur;
    private String nom_Animateur;
    private String prenom_Animateur;
    private String email_Animateur;

    public int getId_Animateur() {
        return id_Animateur;
    }

    public void setId_Animateur(int id_Animateur) {
        this.id_Animateur = id_Animateur;
    }

    public String getNom_Animateur() {
        return nom_Animateur;
    }

    public void setNom_Animateur(String nom_Animateur) {
        this.nom_Animateur = nom_Animateur;
    }

    public String getPrenom_Animateur() {
        return prenom_Animateur;
    }

    public void setPrenom_Animateur(String prenom_Animateur) {
        this.prenom_Animateur = prenom_Animateur;
    }

    public String getEmail_Animateur() {
        return email_Animateur;
    }

    public void setEmail_Animateur(String email_Animateur) {
        this.email_Animateur = email_Animateur;
    }

    public Animateur (int id_Animateur, String nom_Animateur, String prenom_Animateur, String email_Animateur){
        this.id_Animateur=id_Animateur;
        this.nom_Animateur=nom_Animateur;
        this.prenom_Animateur=prenom_Animateur;
        this.email_Animateur=email_Animateur;
    }
}
