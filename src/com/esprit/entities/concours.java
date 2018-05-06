/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author angham
 */
public class concours {

    private int id;

    private String titre;

    private String type;

    private String race;

    private int capacite;

    private Date dateDebut;

    private Date dateFin;

    private int nbparticipant;

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public concours() {
    }

    public concours(int id, String titre, String type, String race, int capacite, Date dateDebut, Date dateFin) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.race = race;
        this.capacite = capacite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "concours{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", race=" + race + ", capacite=" + capacite + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

}
