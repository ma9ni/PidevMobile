/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.esprit.zanimo.Bar;

/**
 *
 * @author makni
 */
public class AjouterFs extends Bar {

    public AjouterFs() {
        super();
        this.hi.setTitle("ajouter fiche soin");
        this.hi.add(new Label("entrer le nom du vet"));
        this.hi.add(new TextField());

    }

}
