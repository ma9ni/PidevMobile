/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class Produit {

    //attributs
    protected int id;
    protected String nom;
    protected String description;
    protected float prix;
    protected String categorie;
    protected String photo;
    protected Date datePublication;
    protected String validitePublication;
    protected int etat;
    protected int IdMembre;

    //constructeurs
    //1
    public Produit() {
        this.datePublication = new Date();
    }

    //2
    public Produit(int id, String nom, String description, float prix, String categorie, String photo, String validitePublication, int IdMembre) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.photo = photo;
        this.datePublication = new Date();
        this.validitePublication = validitePublication;
        this.etat = 1;
        this.IdMembre = IdMembre;
    }

    //getters + settres
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setType(String categorie) {
        this.categorie = categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getValiditePublication() {
        return validitePublication;
    }

    public void setValiditePublication(String validitePublication) {
        this.validitePublication = validitePublication;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdMembre() {
        return IdMembre;
    }

    public void setIdMembre(int IdMembre) {
        this.IdMembre = IdMembre;
    }

    //to string
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", categorie=" + categorie + ", photo=" + photo + ", datePublication=" + datePublication + ", validitePublication=" + validitePublication + ", etat=" + etat + ", IdMembre=" + IdMembre + '}';
    }

}
