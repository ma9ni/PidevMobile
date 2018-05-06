/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author user
 */
public class AfficheVosAnnonce extends Bar{
           private EncodedImage enc;

    public AfficheVosAnnonce(){
        
        super();
        try {
            enc = EncodedImage.create("/s1.jpg");
        } catch (IOException ex) {
        }

            AdoptionService serviceTask=new AdoptionService();
               for (Adoption t : serviceTask.getList2VosAdoption()) {
                                
         
                   Image i = (URLImage.createToStorage(enc, t.getIdAnimal().getNom(),
                           "http://localhost/pi/pi_dev/web/upload/images/" + t.getIdAnimal().getImage() 
                                   + "", URLImage.RESIZE_SCALE));
                  Label race=new Label("race: "+t.getIdAnimal().getRace());
                   Container c= new Container(BoxLayout.x());
                   Container c2= new Container(BoxLayout.y());
                   Container c3= new Container(BoxLayout.x());
                  
                   Button Bedit = new Button("modifier");
                   Button BSupp = new Button("supprimer");
                   
                   BSupp.addActionListener((evt) -> {
                       if(Dialog.show("Attention !", "es ce que vous étes sur de suuprimer votre annoce", "oui", "nom")){
                           System.out.println("oui");
                           System.out.println("id adoption"+ t.getIdAdoption());
                       serviceTask.SupprimerAdoption(t.getIdAdoption());
                       AfficheVosAnnonce ava = new AfficheVosAnnonce();
                       ava.getHi().show();  
                       }
                       
                   });
                   Bedit.addActionListener((evt) -> {
                       ModifierAdoption ma = new ModifierAdoption(t);
                       ma.getHi().show();
                       
                   });
                   
                   Label titre =new Label();
                   if (t.getType().equals("deleger")) {
                       titre.setText( t.getIdAnimal().getRace()+"pour l'adopter definitivement");
                   }else{
                       titre.setText(t.getIdAnimal().getRace()+"pour l'adopter temporairement");
                   }
        
                       titre.addPointerPressedListener((evt) -> {
                           AffichageUneAnnonceAdoptionGui af =new AffichageUneAnnonceAdoptionGui(t);
                           af.getHi().show();
                       });

                       c3.add(Bedit);
                       c3.add(BSupp);
                   c.add(i);
                   c2.add(titre);
                    c2.add(race);
                   c2.add(t.getDescription());
                   c2.add(c3);
                   c.add(c2);

                   hi.add(c);
               }

    }
}
