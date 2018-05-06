/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author user
 */
public class AffichageUneAnnonceAdoptionGui extends Bar{
            private EncodedImage enc;

    public AffichageUneAnnonceAdoptionGui (Adoption adoption){
        super();
        
         try {
            enc = EncodedImage.create("/s1.jpg");
        } catch (IOException ex) {
        }
        Label l1=new Label();
        Label description = new Label(adoption.getDescription());
        Label lieu = new Label(adoption.getLieu());
        Button ButtonReclamation = new  Button("ajouter une reclamation");
        Button ButtonContact = new  Button("Contacter l'annonceur");
        
        ButtonContact.addActionListener((evt) -> {
            
            ContactAnnonceur c =new ContactAnnonceur(adoption.getIdMembre());
            c.getHi().show();
        });
        ButtonReclamation.addActionListener((evt) -> {
            
            Reclamation c =new Reclamation(adoption);
            c.getHi().show();
        });
        
        Container c =new Container(BoxLayout.y());
        c.add(l1);
        c.add(enc);
        c.add(description);
        c.add(lieu);
        c.add(ButtonContact);
        c.add(ButtonReclamation);
        hi.add(c);
        hi.show();
        
    }
    
}
