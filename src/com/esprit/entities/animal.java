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
 * @author salah
 */
public class animal {

    private int id;
    private User id_membre;

    private String nom;
    private String nomproprietaire;
    private String description;
    private String sexe;
    private Date datedenaissance;
    private String race;
    private String image;
    private int id_membree;

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public animal() {
    }

    public int getId_membree() {
        return id_membree;
    }

    public void setId_membree(int id_membree) {
        this.id_membree = id_membree;
    }

    public animal(int id, User id_membre, String nom, String nomproprietaire, String description, String sexe, Date datedenaissance, String race, String image) {
        this.id = id;
        this.id_membre = id_membre;
        this.nom = nom;
        this.nomproprietaire = nomproprietaire;
        this.description = description;
        this.sexe = sexe;
        this.datedenaissance = datedenaissance;
        this.race = race;
        this.image = image;
    }

    public animal(int id, String nom, String description) {
        this.id = id;

        this.nom = nom;

        this.description = description;

    }

    public int getId() {
        return id;
        //hhhh
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomproprietaire() {
        return nomproprietaire;
    }

    public void setNomproprietaire(String nomproprietaire) {
        this.nomproprietaire = nomproprietaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SimpleDateFormat getFormater() {
        return formater;
    }

    public void setFormater(SimpleDateFormat formater) {
        this.formater = formater;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;

    }

    public animal(String nom, String nomproprietaire, String image) {
        this.nom = nom;
        this.nomproprietaire = nomproprietaire;
        this.image = image;
    }

//    @Override
//    public String toString() {
//        return "animal{" + "id=" + id + ", id_membre=" + id_membre + ", nom=" + nom + ", nomproprietaire=" + nomproprietaire + ", description=" + description + ", sexe=" + sexe + ", datedenaissance=" + datedenaissance + ", race=" + race + ", image=" + image + ", formater=" + formater + '}';
//    }
    @Override
    public String toString() {
        return nom;
    }
}
