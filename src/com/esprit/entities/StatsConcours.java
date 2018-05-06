/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author angham
 */
public class StatsConcours {

    private String type;
    private double nbre;

    public StatsConcours() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getNbre() {
        return nbre;
    }

    public void setNbre(double nbre) {
        this.nbre = nbre;
    }

    @Override
    public String toString() {
        return "StatsConcours{" + "type=" + type + ", nbre=" + nbre + '}';
    }

}
