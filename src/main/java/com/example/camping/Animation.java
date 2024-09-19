package com.example.camping;

public class Animation {
    private int id_Animation;
    private String nom_Animation;
    private String descriptif_Animation;

    public  Animation(int id_Animation, String nom_Animation, String descriptif_Animation){
        this.id_Animation=id_Animation;
        this.nom_Animation=nom_Animation;
        this.descriptif_Animation=descriptif_Animation;
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


}
