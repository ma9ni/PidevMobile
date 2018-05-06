/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;

/**
 *
 * @author user
 */
public class Reclamation extends Bar{
    
    public Reclamation(Adoption adoption){
      Label lsujet = new Label("motif");
        Label lmessage = new Label("message");
        
        
        TextField tsujet = new TextField();
        TextArea tmesage = new TextArea();
        
        
        Button BenvoyerMessage = new Button("Envoyer la reclamation");
        
        BenvoyerMessage.addActionListener((evt) -> {
            AdoptionService us= new AdoptionService();
            us.ajoutReclamation(adoption.getIdAdoption(), tsujet.getText(), tmesage.getText());
            
            Dialog.show("", "reclamation envoyer avec sucess", "ok","cancel");
            AffichageUneAnnonceAdoptionGui a= new AffichageUneAnnonceAdoptionGui(adoption);
            a.getHi().show();
            
            
        });
        
        
        Container c=  new Container(BoxLayout.y());
        
        c.add(lsujet);
        c.add(tsujet);
        c.add(lmessage);
        c.add(tmesage);
        c.add(BenvoyerMessage);
        hi.setTitle("signaler l'annonce");
        hi.add(c);
        hi.show();
}
}
