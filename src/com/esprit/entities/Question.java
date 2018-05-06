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
public class Question {

    private int id;
    private String question;
    private Reponse idReponse;
    private int etat;

    public Question() {
    }

    public Question(String question, int etat) {
        this.question = question;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Reponse getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Reponse idReponse) {
        this.idReponse = idReponse;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

}
