/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author user
 */
public class User {

    private int id;
    private String username;
    private String username_canonical;
    private String email_canonical;
    private String email;
    private String enabled;
    private String pasword;
    private int num_tel;
    private int confirmation;
    private String image;
    private String adresse;
    private String gouvernorat;
    private float note;
    private String password_requested_at;
    private String confirmation_token;
    private String last_login;
    private String role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String email, String pasword, String image, String adresse, String gouvernorat) {
        this.username = username;
        this.email = email;
        this.pasword = pasword;
        this.image = image;
        this.adresse = adresse;
        this.gouvernorat = gouvernorat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(String password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email_canonical=" + email_canonical + ", email=" + email + ", enabled=" + enabled + ", pasword=" + pasword + ", num_tel=" + num_tel + ", confirmation=" + confirmation + ", image=" + image + ", adresse=" + adresse + ", gouvernorat=" + gouvernorat + ", note=" + note + ", password_requested_at=" + password_requested_at + ", confirmation_token=" + confirmation_token + ", last_login=" + last_login + '}';
    }

}
