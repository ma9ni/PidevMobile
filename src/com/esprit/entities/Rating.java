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
public class Rating {

    private int id;
    private int note;
    private String commentaire;
    private Date datenote;
    private User idUser;
    private User idMembreCO;

    public Rating() {
    }

    public Rating(int id, int note, String commentaire, Date datenote, User idUser, User idMembreCO) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.datenote = datenote;
        this.idUser = idUser;
        this.idMembreCO = idMembreCO;
    }

    public Rating(int note, String commentaire, Date datenote, User idUser, User idMembreCO) {
        this.note = note;
        this.commentaire = commentaire;
        this.datenote = datenote;
        this.idUser = idUser;
        this.idMembreCO = idMembreCO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDatenote() {
        return datenote;
    }

    public void setDatenote(Date datenote) {
        this.datenote = datenote;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public User getIdMembreCO() {
        return idMembreCO;
    }

    public void setIdMembreCO(User idMembreCO) {
        this.idMembreCO = idMembreCO;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", datenote=" + datenote + ", idUser=" + idUser + ", idMembreCO=" + idMembreCO + '}';
    }

}
