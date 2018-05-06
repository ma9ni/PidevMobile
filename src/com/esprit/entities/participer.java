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
public class participer {

    int idanimal;
    int idconcour;

    public participer(int idanimal, int idconcour) {
        this.idanimal = idanimal;
        this.idconcour = idconcour;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public int getIdconcour() {
        return idconcour;
    }

    public void setIdconcour(int idconcour) {
        this.idconcour = idconcour;
    }

    @Override
    public String toString() {
        return "participer{" + "idanimal=" + idanimal + ", idconcour=" + idconcour + '}';
    }

}
