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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.entities.User;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AffichageAdoptionGui extends Bar {

    //   Form f;
    SpanLabel lb;
    private EncodedImage enc;

    public AffichageAdoptionGui() {
        super();
        // f = new Form();

        TextField recherche = new TextField();

        recherche.addActionListener((evt) -> {
            String unerecherche = recherche.getText();
            ArrayList<Adoption> List1 = new ArrayList<>();
            AdoptionService As = new AdoptionService();
            ArrayList<Adoption> List2 = As.getList2();
            for (Adoption adoption : List2) {
                if (adoption.getIdAnimal().getRace().indexOf(unerecherche) > -1) {
                    List1.add(adoption);
                    System.out.println("_______" + adoption.getIdAnimal().getRace());
                }

            }
            if (recherche.getText().length() != 0) {
                this.hi.removeAll();
                this.hi.refreshTheme();
                for (Adoption houlala : List1) {
                    Affiche(houlala);
                }

            } else {
                this.hi.removeAll();
                this.hi.refreshTheme();
                this.hi.add(new Label("dsl y a pas0"));
            }

        });
        Button BAjout = new Button("ajouter votre Annonce");
        Button BVoirVosAnnonces = new Button("Voir Vos Annonces");

        BVoirVosAnnonces.addActionListener((evt) -> {
            AfficheVosAnnonce ava = new AfficheVosAnnonce();
            ava.getHi().show();
        });
        BAjout.addActionListener((evt) -> {
            AjoutAdoptionGui h = new AjoutAdoptionGui();
            h.getHi().show();

        });

        BVoirVosAnnonces.addActionListener((evt) -> {
        });
        hi.add(recherche);
        lb = new SpanLabel("");
        hi.add(BAjout);
        hi.add(BVoirVosAnnonces);
        hi.add(lb);
        try {
            enc = EncodedImage.create("/s1.jpg");
        } catch (IOException ex) {
        }

        AdoptionService serviceTask = new AdoptionService();
        for (Adoption t : serviceTask.getList2()) {
            Affiche(t);

        }

    }

    public void Affiche(Adoption t) {

        Image i = (URLImage.createToStorage(enc, t.getIdAnimal().getNom(), "http://localhost/pi_dev-master/web/upload/images/" + t.getIdAnimal().getImage() + "", URLImage.RESIZE_SCALE));

        // ImageViewer img2 = new ImageViewer(i.fill(120, 130));
        //affiche de l'animal
        Label race = new Label("race: " + t.getIdAnimal().getRace());
        Container c = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.y());

        Label titre = new Label();
        if (t.getType().equals("deleger")) {
            titre.setText(t.getIdAnimal().getRace() + "pour l'adopter definitivement");
        } else {
            titre.setText(t.getIdAnimal().getRace() + "pour l'adopter temporairement");
        }

        titre.addPointerPressedListener((evt) -> {
            AffichageUneAnnonceAdoptionGui af = new AffichageUneAnnonceAdoptionGui(t);
            af.getHi().show();
        });
        Label tiré = new Label("");
        c.add(tiré);
        c.add(i);
        c2.add(titre);
        c2.add(race);
        c2.add(t.getDescription());
        c.add(c2);
//c.add(tiré);

        hi.add(c);

    }
}
