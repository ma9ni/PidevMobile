/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.concours;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.concours;
import com.esprit.services.concours.concoursService;
import com.esprit.zanimo.Bar;

/**
 *
 * @author angham
 */
public class AjouterConcours extends Bar {

    Label titreEror;
    Label raceEror;
    Label capaEror;

    public AjouterConcours() {
        super();
        this.hi.setTitle("ajouter concours");

        //pour le titre du concours
        Label titreLabel = new Label("Titre");
        TextField titreArea = new TextField("", "Titre", 12, TextArea.ANY);
        titreEror = new Label("*");
        titreEror.getAllStyles().setFgColor(0xff0000);
        titreEror.setVisible(false);
        Container titreContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        titreContainer.add(titreLabel);
        titreContainer.add(titreArea);
        titreContainer.add(titreEror);

        //pour le type du concours
        Label typeLabel = new Label("Type");
        ComboBox typeCombo = new ComboBox();
        String[] lestypes = {"chien", "chat", "lapin", "poisson"};
        for (int i = 0; i < 4; i++) {// on a 4 car nous avons 4 type de concours
            typeCombo.addItem(lestypes[i]);
        }
        Container TypeContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        TypeContainer.add(typeLabel);
        TypeContainer.add(typeCombo);

        //pour la race des animaux du concours
        Label raceLabel = new Label("Race");
        TextField raceArea = new TextField("", "Race", 12, TextArea.ANY);
        raceEror = new Label("*");
        raceEror.getAllStyles().setFgColor(0xff0000);
        raceEror.setVisible(false);
        Container raceContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        raceContainer.add(raceLabel);
        raceContainer.add(raceArea);
        raceContainer.add(raceEror);

        //pour la capacité du concours
        Label capaLabel = new Label("capacité");
        TextField capacArea = new TextField("", "capacité", 12, TextArea.ANY);
        capaEror = new Label("*");
        capaEror.getAllStyles().setFgColor(0xff0000);
        capaEror.setVisible(false);
        Container capacContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        capacContainer.add(capaLabel);
        capacContainer.add(capacArea);
        capacContainer.add(capaEror);

        //pour la date de debut  du concours
        Label ddLabel = new Label("date debut");
        Picker ddPicker = new Picker();
        Label ddEror = new Label("*");
        ddEror.getAllStyles().setFgColor(0xff0000);
        ddEror.setVisible(false);
        Container ddContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ddContainer.add(ddLabel);
        ddContainer.add(ddPicker);
        ddContainer.add(ddEror);

        //pour la date de fin  du concours
        Label dfLabel = new Label("date debut");
        Picker dfPicker = new Picker();
        Label dfEror = new Label("*");
        dfEror.getAllStyles().setFgColor(0xff0000);
        dfEror.setVisible(false);
        Container dfContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        dfContainer.add(dfLabel);
        dfContainer.add(dfPicker);
        dfContainer.add(dfEror);

        //pour que les date soient affiché l'une a cote de l'autre
        Container datesContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datesContainer.add(ddContainer);
        datesContainer.add(dfContainer);

        //boutton valider
        Button btnValider = new Button("Valider");
        btnValider.addActionListener((evt) -> {
            concours temp = new concours();
            temp.setTitre(titreArea.getText());
            temp.setType(typeCombo.getSelectedItem().toString());
            temp.setCapacite(Integer.parseInt(capacArea.getText()));
            temp.setRace(raceArea.getText());
            temp.setDateDebut(ddPicker.getDate());
            temp.setDateFin(dfPicker.getDate());
            concoursService tempService = new concoursService();
            tempService.ajoutConcours(temp);
            //al la fin de notre action de l'ajout du concours un dialog va s'afficher et nous allons rediger l'utilisateur a la liste des concours
            Dialog.show("felicitation", "le concours est ajouter et il est desormé visible pour tt utilisateur", "OK", null);
            AffichageConcours inte = new AffichageConcours();
            inte.getHi().show();
        });
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnContainer.add(btnValider);

        this.hi.add(titreContainer);
        this.hi.add(TypeContainer);
        this.hi.add(raceContainer);
        this.hi.add(capacContainer);
        this.hi.add(datesContainer);
        this.hi.add(btnContainer);
    }

}
