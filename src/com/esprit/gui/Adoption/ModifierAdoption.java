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
import com.codename1.ui.TextField;
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
public class ModifierAdoption extends Bar {

    private EncodedImage enc;

    public ModifierAdoption(Adoption adoption) {
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

        //affiche l'annonce
        Label l1 = new Label();

        if (adoption.getType().equals("deleger")) {
            l1.setText(adoption.getIdAnimal().getRace() + " a adopter temporairement");

        } else {

            l1.setText(adoption.getIdAnimal().getRace() + " a adopter définitivement");

        }

        Label ldes = new Label("description");
        Label llieu = new Label("lieu");
        TextField description = new TextField(adoption.getDescription());
        TextField lieu = new TextField(adoption.getLieu());
        Button modifierButton = new Button("modifier");
        modifierButton.addActionListener((evt) -> {

            Adoption a = new Adoption();
            System.out.println("c'est l'd adoption " + adoption.getIdAdoption());
            a.setIdAdoption(adoption.getIdAdoption());
            a.setDescription(description.getText());
            a.setLieu(lieu.getText());

            AdoptionService as = new AdoptionService();
            as.modifierAdoption(a);
            System.out.println("modification effectuer avec sucess");
            Dialog.show("Sucess", "modification effectuer avec suces", "ok", "cancel");
            AfficheVosAnnonce av = new AfficheVosAnnonce();
            av.getHi().show();

        });

        Container c = new Container(BoxLayout.y());
        c.add(l1);
        c.add(i);
        c.add(race);
        c.add(nom);
        c.add(ldes);
        c.add(description);
        c.add(llieu);
        c.add(lieu);
        c.add(modifierButton);

        hi.add(c);
        hi.show();

    }
}
