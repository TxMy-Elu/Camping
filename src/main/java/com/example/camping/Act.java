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

    /** Constructeur de Activité
     *
     * @param animateur
     * @param creneaux
     */

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

    /** Get Horaires
     *
     * @return horaires
     */
    public String getHoraires() {
        return horaires;
    }

    /** Set Horaires
     *
     * @param horaires
     */

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    /** Get Animateur
     *
     * @return animateur
     */

    public Animateur getAnimateur() {
        return animateur;
    }

    /** Set Animateur
     *
     * @param animateur
     */
    public void setAnimateur(Animateur animateur) {
        this.animateur = animateur;
    }

    /** Get Creneaux
     *
     * @return creneaux
     */
    public Creneaux getCreneaux() {
        return creneaux;
    }

    /** Set Creneaux
     *
     * @param creneaux
     */
    public void setCreneaux(Creneaux creneaux) {
        this.creneaux = creneaux;
    }

    /** Get Lundi
     *
     * @return lundi
     */
    public String getLundi() {
        return lundi;
    }

    /** Set Lundi
     *
     * @param lundi
     */
    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    /** Get Mardi
     *
     * @return mardi
     */
    public String getMardi() {
        return mardi;
    }

    /** Set Mardi
     *
     * @param mardi
     */
    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    /** Get Mercredi
     *
     * @return mercredi
     */
    public String getMercredi() {
        return mercredi;
    }

    /** Set Mercredi
     *
     * @param mercredi
     */
    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    /** Get Jeudi
     *
     * @return jeudi
     */
    public String getJeudi() {
        return jeudi;
    }

    /** Set Jeudi
     *
     * @param jeudi
     */
    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    /** Get Vendredi
     *
     * @return vendredi
     */
    public String getVendredi() {
        return vendredi;
    }

    /** Set Vendredi
     *
     * @param vendredi
     */
    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    /** Get Id de DateHeure
     *
     * @return
     */
    public String getId_DateHeure() {
        return creneaux.getDateHeure().toString();
    }

    /** Get Nom de Animation
     *
     * @return Nom Animation
     */
    public String getNom_Animation() {
        return animation.getNom_Animation();
    }

    /** toString
     *
     * @return animateur,creneaux, animation
     */
    @Override
    public String toString() {
        return animateur + "\n" + creneaux + "\n" + animation;
    }

    /** HashMap de Animateur,Creneaux
     *
     * @param currentDate
     * @return currentDate
     */
    public static HashMap<Animateur, Creneaux> getAct(LocalDate currentDate) {
        return DatabaseHelper.getAct(currentDate);
    }
}
