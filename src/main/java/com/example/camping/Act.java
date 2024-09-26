package com.example.camping;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;

public class Act {


    private Animateur animateur;
    private Creneaux creneaux;

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

    public Creneaux getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(Creneaux creneaux) {
        this.creneaux = creneaux;
    }

    @Override
    public String toString() {
        return creneaux.getLieu_Creneaux() + " - " + animateur.getNom_Animateur() + " " + animateur.getPrenom_Animateur();
    }

    public static HashMap<Animateur, Creneaux> getAct() {
        ConnexionBDD c = new ConnexionBDD();
        HashMap<Animateur, Creneaux> lesAct = new HashMap<>();
        if (c != null) {
            try {
                String requete = "SELECT * FROM relation1 inner join animateur on animateur.id_animateur = relation1.id_animateur inner join creneaux on creneaux.id_creneaux = relation1.id_creneaux order by creneaux.date_heure asc";
                Statement stmt = c.getConnection().createStatement();
                ResultSet res = stmt.executeQuery(requete);

                while (((ResultSet) res).next()) {
                    Animateur _animateur = new Animateur(res.getInt("id_animateur"), res.getString("nom"), res.getString("prenom"), res.getString("email"));
                    Date date = res.getDate("date_heure");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Creneaux _creneaux = new Creneaux(res.getInt("id_creneaux"), cal, res.getString("lieu"), res.getInt("duree"));
                    Act e = new Act(_animateur, _creneaux);
                    lesAct.put(_animateur, _creneaux);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lesAct;
    }
}
