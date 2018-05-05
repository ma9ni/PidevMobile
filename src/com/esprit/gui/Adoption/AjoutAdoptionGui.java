/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.entities.animal;
import com.esprit.gui.home.Homegui;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AjoutAdoptionGui extends Bar{
    
    public AjoutAdoptionGui(){
        
        super();
        this.hi.setTitle("ajouter Annonce Adoption");
        Label ltype = new Label("choisir le type de l'annonce");
        String[] lesTYpes = {"Donner temporairement votre animal","donner définitivement votre animal"};
        ComboBox type = new ComboBox();
        for (int i = 0; i < 2; i++) {
            type.addItem(lesTYpes[i]);
            
        }
        Label llieu = new Label("lieu");
        TextField   lieu = new TextField("","ajouter l'adresse");
        Label lanimal = new Label("choisir votre animal");
        
        ComboBox  cmb = new ComboBox<>();
              
        
       ArrayList<animal> anim = new ArrayList<>();

       AdoptionService ad = new AdoptionService();
        anim.addAll(ad.getListanimal2(6));

        for (animal object : anim) {
            cmb.addItem(object.getNom());
        }
        Label ldescription = new Label("ajouter une description");
        TextField description = new TextField("","",12,TextArea.ANY);
        
        Button ButtonAjout = new Button("ajouter");

        ButtonAjout.addActionListener((evt) -> {
            animal a = anim.get(cmb.getSelectedIndex());
            Adoption adoption = new Adoption(0, lieu.getText(), description.getText()
                    , type.getSelectedItem().toString(), a);
             Dialog.show("felicitation", " votre annonce d'adoption est Ajouter", "ok", null);
             
             AdoptionService as = new AdoptionService();
             as.AjouterAdoption(adoption);
            AffichageAdoptionGui h = new AffichageAdoptionGui();
            h.getHi().show();
            
        });
        Container c = new Container(BoxLayout.y());
        c.add(ltype);
        c.add(type);
        c.add(llieu);
        c.add(lieu);
        c.add(ldescription);
        c.add(description);
        c.add(lanimal);
        c.add(cmb);
        c.add(ButtonAjout);
        
        hi.add(c);
        
        
        
        
        
    }
    
}
