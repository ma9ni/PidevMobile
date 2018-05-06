/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.entities.User;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;
import java.io.IOException;


/**
 *
 * @author user
 */
public class AffichageAdoptionGui extends Bar{
    
  //   Form f;
    SpanLabel lb;
        private EncodedImage enc;

        public AffichageAdoptionGui() {
        super();
       // f = new Form();
            Button BAjout= new Button("ajouter votre Annonce");
            BAjout.addActionListener((evt) -> {
               AjoutAdoptionGui h= new AjoutAdoptionGui();
                           h.getHi().show();

            });
       
        lb = new SpanLabel("");
        hi.add(BAjout);
        hi.add(lb);
        try {
            enc = EncodedImage.create("/s1.jpg");
        } catch (IOException ex) {
        }

            AdoptionService serviceTask=new AdoptionService();
               for (Adoption t : serviceTask.getList2()) {
                           
                   Container c= new Container(BoxLayout.x());
                   Container c2= new Container(BoxLayout.y());
                   Label titre =new Label();
                   if (t.getType().equals("deleger")) {
                       titre.setText("pour l'adopter definitivement");
                   }else{
                       titre.setText("pour l'adopter temporairement");
                   }
        
                       titre.addPointerPressedListener((evt) -> {
                           AffichageUneAnnonceAdoptionGui af =new AffichageUneAnnonceAdoptionGui(t);
                           af.getHi().show();
                       });

                   c.add(enc);
                   c2.add(titre);
                   c2.add(t.getDescription());
                   c.add(c2);

                   hi.add(c);
               }

      
         
    }

    
}
