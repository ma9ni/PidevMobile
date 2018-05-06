/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.animal;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.animal;
import com.esprit.services.animal.animalservices;
import com.esprit.services.animal.animalsusersevices;
import com.esprit.zanimo.Bar;

/**
 *
 * @author salah
 */
public class modifier_animal extends Bar {

    Button btn;
    Button supp;
    animalservices serviceTask = new animalservices();
    animal an;

    public modifier_animal(animal an) {

        super();
        System.out.println("iiiii" + afficher_user_animals.ani);
//        for (animal anim : serviceTask.getList2()) {
//            if (anim.getId() == afficher_user_animals.ani) {
//                an = new animal(anim.getId(), anim.getNom(), anim.getDescription());
//            }
//        }

        Container hii = new Container(new TableLayout(2, 2));
        Label titreLabel = new Label("Nom");
        TextField titreArea = new TextField("", "Nom", 12, TextArea.ANY);
        Label descriptionLabel = new Label("Description : ");
        TextArea descriptionArea = new TextArea(3, 20);
        titreArea.setText(an.getNom());
        descriptionArea.setText(an.getDescription());
        hii.add(titreLabel);
        hii.add(titreArea);
        hii.add(descriptionLabel);
        hii.add(descriptionArea);
        btn = new Button("modifier");
        supp = new Button("supprimer");
        btn.addActionListener((evt) -> {
            animal animalmodifier = new animal(an.getId(), titreArea.getText(), descriptionArea.getText());
            System.out.println("animal a modifer " + animalmodifier);
            animalsusersevices anse = new animalsusersevices();
            anse.modifier_animal(animalmodifier);
            afficher_user_animals aff = new afficher_user_animals();
            aff.getHi().show();

        });

        hii.add(btn);
        hii.add(supp);
        this.hi.add(hii);

    }
}
