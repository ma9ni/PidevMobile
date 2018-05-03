/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Ahmed
 */
public class Categorie {

    // attributs
    private int idCategorie;
    private String nom;

    // constructeurs
    public Categorie() {
    }

    // getters + settres
    public int getId() {
        return idCategorie;
    }

    public void setId(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //toString
    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + idCategorie + ", nom=" + nom + '}';
    }

}
