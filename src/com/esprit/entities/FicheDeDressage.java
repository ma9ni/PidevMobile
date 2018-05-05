/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author makni
 */
public class FicheDeDressage {

    private int id;
    private User id_membre;
    private String specialite;
    private float displine;
    private float obeissance;
    private float accompagnement;
    private float interception;
    private float noteTotal;
    private Date dateDebut;
    private Date dateFin;
    private animal id_animal;
    private int etat;

    public FicheDeDressage() {
    }

    public FicheDeDressage(int id, User id_membre, String specialite, float displine, float obeissance, float accompagnement, float interception, float noteTotal, Date dateDebut, Date dateFin, animal id_animal, int etat) {
        this.id = id;
        this.id_membre = id_membre;
        this.specialite = specialite;
        this.displine = displine;
        this.obeissance = obeissance;
        this.accompagnement = accompagnement;
        this.interception = interception;
        this.noteTotal = noteTotal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_animal = id_animal;
        this.etat = etat;
    }

    public FicheDeDressage(User id_membre, String specialite, float displine, float obeissance, float accompagnement, float interception, float noteTotal, Date dateDebut, Date dateFin, animal id_animal, int etat) {

        this.id_membre = id_membre;
        this.specialite = specialite;
        this.displine = displine;
        this.obeissance = obeissance;
        this.accompagnement = accompagnement;
        this.interception = interception;
        this.noteTotal = noteTotal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_animal = id_animal;
        this.etat = etat;
    }

    public FicheDeDressage(int id, String specialite, float displine, float obeissance, float accompagnement, float interception, float noteTotal, Date dateDebut, Date dateFin) {
        this.id = id;

        this.specialite = specialite;
        this.displine = displine;
        this.obeissance = obeissance;
        this.accompagnement = accompagnement;
        this.interception = interception;
        this.noteTotal = noteTotal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getId_membre() {
        return id_membre;
    }

    public void setId_membre(User id_membre) {
        this.id_membre = id_membre;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public float getDispline() {
        return displine;
    }

    public void setDispline(float displine) {
        this.displine = displine;
    }

    public float getObeissance() {
        return obeissance;
    }

    public void setObeissance(float obeissance) {
        this.obeissance = obeissance;
    }

    public float getAccompagnement() {
        return accompagnement;
    }

    public void setAccompagnement(float accompagnement) {
        this.accompagnement = accompagnement;
    }

    public float getInterception() {
        return interception;
    }

    public void setInterception(float interception) {
        this.interception = interception;
    }

    public float getNoteTotal() {
        return noteTotal;
    }

    public void setNoteTotal(float noteTotal) {
        this.noteTotal = noteTotal;
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

    public animal getId_animal() {
        return id_animal;
    }

    public void setId_animal(animal id_animal) {
        this.id_animal = id_animal;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "FicheDeDressage{" + "id=" + id + ", id_membre=" + id_membre + ", specialite=" + specialite + ", displine=" + displine + ", obeissance=" + obeissance + ", accompagnement=" + accompagnement + ", interception=" + interception + ", noteTotal=" + noteTotal + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", id_animal=" + id_animal + ", etat=" + etat + '}';
    }

}
