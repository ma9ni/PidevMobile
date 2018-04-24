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
public class sosDisparition {
    
    
    private int id;
    private String nomproprietaire;
    private String description ;
    private Date Date;
    private String race ;
    private int num_tel;
    private String adresse;
      private int id_membre;
      private String image ;
      
    
    
    
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
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

    public sosDisparition() {
    }

    public sosDisparition(int id, String nomproprietaire, String description, Date Date, String race, int num_tel, String adresse, int id_membre, String image) {
        this.id = id;
        this.nomproprietaire = nomproprietaire;
        this.description = description;
        this.Date = Date;
        this.race = race;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.id_membre = id_membre;
        this.image = image;
    }

    @Override
    public String toString() {
        return "sosDisparition{" + "id=" + id + ", nomproprietaire=" + nomproprietaire + ", description=" + description + ", Date=" + Date + ", race=" + race + ", num_tel=" + num_tel + ", adresse=" + adresse + ", id_membre=" + id_membre + ", image=" + image + ", formater=" + formater + '}';
    }
    
    
    
    
}
