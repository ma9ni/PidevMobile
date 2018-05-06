/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.User;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;

/**
 *
 * @author user
 */
public class ContactAnnonceur extends Bar{
    
    public ContactAnnonceur(User user){
        Label lsujet = new Label("Sujet");
        Label lmessage = new Label("message");
        
        
        TextField tsujet = new TextField();
        TextArea tmesage = new TextArea();
        
        
        Button BenvoyerMessage = new Button("Envoyer le message");
        BenvoyerMessage.addActionListener((evt) -> {
            AdoptionService as = new AdoptionService();
            as.envoyerMessage();
        });
        
        
        Container c=  new Container(BoxLayout.y());
        
        c.add(lsujet);
        c.add(tsujet);
        c.add(lmessage);
        c.add(tmesage);
        c.add(BenvoyerMessage);
        hi.setTitle("contacter l'annonceur");
        hi.add(c);
        hi.show();
        
        
        
        
        
        
        
        
        
    }
    
}
