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
 * @author makni
 */
public class FicheDeSoin {

    //Les attributs
    private int id;
    private int id_membre;
    private String observation;
    private String medicament;
    private Date prochainRDV;
    private int id_animal;
    private Date dateCreation;
    private int etat;

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public Date getProchainRDV() {
        return prochainRDV;
    }

    public void setProchainRDV(Date prochainRDV) {
        this.prochainRDV = prochainRDV;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public FicheDeSoin() {
    }

    public FicheDeSoin(int id, int id_membre, String observation, String medicament, Date prochainRDV, int id_animal, Date dateCreation, int etat) {
        this.id = id;
        this.id_membre = id_membre;
        this.observation = observation;
        this.medicament = medicament;
        this.prochainRDV = prochainRDV;
        this.id_animal = id_animal;
        this.dateCreation = dateCreation;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "FicheDeSoin{" + "id=" + id + ", id_membre=" + id_membre + ", observation=" + observation + ", medicament=" + medicament + ", prochainRDV=" + prochainRDV + ", id_animal=" + id_animal + ", dateCreation=" + dateCreation + ", etat=" + etat + ", formater=" + formater + '}';
    }
    

}
