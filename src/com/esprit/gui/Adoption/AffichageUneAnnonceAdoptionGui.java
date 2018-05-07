/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author user
 */
public class AffichageUneAnnonceAdoptionGui extends Bar {

    private EncodedImage enc;

    public AffichageUneAnnonceAdoptionGui(Adoption adoption) {
        super();

        try {
            enc = EncodedImage.create("/s1.jpg");
        } catch (IOException ex) {
        }
        Image i = (URLImage.createToStorage(enc, adoption.getIdAnimal().getNom(), "http://localhost/pi_dev-master/web/upload/images/" + adoption.getIdAnimal().getImage() + "", URLImage.RESIZE_SCALE));

        // ImageViewer img2 = new ImageViewer(i.fill(120, 130));
        //affiche de l'animal
        Label race = new Label("race animal: " + adoption.getIdAnimal().getRace());
        Label nom = new Label("nom animal: " + adoption.getIdAnimal().getNom());
        Label Lieu = new Label("Lieu : " + adoption.getLieu());

        //affiche l'annonce
        Label l1 = new Label();

        if (adoption.getType().equals("deleger")) {
            l1.setText(adoption.getIdAnimal().getRace() + " a adopter temporairement");

        } else {

            l1.setText(adoption.getIdAnimal().getRace() + " a adopter définitivement");

        }
        Label description = new Label(adoption.getDescription());
        Label lieu = new Label(adoption.getLieu());
        Button ButtonReclamation = new Button("Signialer l'annonce");
        Button ButtonContact = new Button("Contacter l'annonceur");

        ButtonContact.addActionListener((evt) -> {

            ContactAnnonceur c = new ContactAnnonceur(adoption.getIdMembre());
            c.getHi().show();
        });
        ButtonReclamation.addActionListener((evt) -> {

            Reclamation c = new Reclamation(adoption);
            c.getHi().show();
        });

        Container c = new Container(BoxLayout.y());
        c.add(l1);
        c.add(i);
        c.add(race);
        c.add(nom);
        c.add(Lieu);

        c.add(description);
        c.add(ButtonContact);
        c.add(ButtonReclamation);
        hi.add(c);
        hi.show();

    }

}
