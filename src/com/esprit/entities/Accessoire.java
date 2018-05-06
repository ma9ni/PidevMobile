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
public class Accessoire extends Produit {

    //attributs
    private String qteStock;

    //constructeurs
    //1
    public Accessoire() {
        super();
    }

    //2
    public Accessoire(String qteStock, int id, String nom, String description, float prix, String categorie, String photo, String validitePublication, int IdMembre) {
        super(id, nom, description, prix, categorie, photo, validitePublication, IdMembre);
        this.qteStock = qteStock;
    }

    //getters and setters
    public String getQteStock() {
        return qteStock;
    }

    public void setQteStock(String qteStock) {
        this.qteStock = qteStock;
    }

    @Override
    public String toString() {
        return super.toString() + "Accessoire{" + "qteStock=" + qteStock + '}';
    }

}
