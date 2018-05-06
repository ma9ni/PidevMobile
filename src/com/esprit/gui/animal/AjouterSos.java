/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.animal;

import com.esprit.zanimo.Bar;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.animal;
import com.esprit.entities.sosDisparition;
import com.esprit.gui.home.Homegui;
import com.esprit.services.animal.sosservices;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author salah
 */
public class AjouterSos extends Bar {

    public AjouterSos() {
        super();
        this.hi.setTitle("Ajouter un SOS");
        //pour le titre de l'accessoire
        Label NomproprietaireLabel = new Label("Nomproprietaire");
        TextField NomproprietaireArea = new TextField("", "Nomproprietaire", 12, TextArea.ANY);
        Label NomproprietaireEror = new Label("*");
        NomproprietaireEror.getAllStyles().setFgColor(0xff0000);
        NomproprietaireEror.setVisible(false);
        Container titreContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        titreContainer.add(NomproprietaireLabel);
        titreContainer.add(NomproprietaireArea);
        titreContainer.add(NomproprietaireEror);

        //pour la description de l'animal
        Label descriptionLabel = new Label("Description : ");
        TextArea descriptionArea = new TextArea(3, 20);
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);
        descriptionContainer.add(descriptionArea);

        //pour la validité
        //  Label sexeLabel = new Label("Sexe");
        //  String[] sexechoise = {"Male", "Femelle"};
        //  ComboBox sexeBox = new ComboBox();
        // for (int i = 0; i < 2; i++) {
        //     sexeBox.addItem(sexechoise[i]);
        // }
        // Container validiteContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        // validiteContainer.add(sexeLabel);
        // validiteContainer.add(sexeBox);
        //pour la date de naissance
        Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker datepiker = new Picker();
        Label temp = new Label("date de naissance");
        dateContainer.add(temp);
        dateContainer.add(datepiker);

        //pour la race
        Label RaceLabel = new Label("Race");
        TextField RaceArea = new TextField("", "Race", 12, TextArea.ANY);
        Label RaceEror = new Label("*");
        RaceEror.getAllStyles().setFgColor(0xff0000);
        RaceEror.setVisible(false);
        Container RaceContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        RaceContainer.add(RaceLabel);
        RaceContainer.add(RaceArea);
        RaceContainer.add(RaceEror);
        //pour la tel
        Label telLabel = new Label("Tel");
        TextField telArea = new TextField("", "Tel", 12, TextArea.ANY);
        Label telEror = new Label("*");
        telEror.getAllStyles().setFgColor(0xff0000);
        telEror.setVisible(false);
        Container telContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        RaceContainer.add(telLabel);
        RaceContainer.add(telArea);
        RaceContainer.add(telEror);

        //pour la lieu
        Label lieuLabel = new Label("lieu");
        TextField lieuArea = new TextField("", "lieu", 12, TextArea.ANY);
        Label lieuEror = new Label("*");
        lieuEror.getAllStyles().setFgColor(0xff0000);
        lieuEror.setVisible(false);
        Container lieuContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        lieuContainer.add(lieuLabel);
        lieuContainer.add(lieuArea);
        lieuContainer.add(lieuEror);

        //pour la adresse
        Label adresseLabel = new Label("adresse");
        TextField adresseArea = new TextField("", "adresse", 12, TextArea.ANY);
        Label adresseEror = new Label("*");
        adresseEror.getAllStyles().setFgColor(0xff0000);
        adresseEror.setVisible(false);
        Container adresseContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        adresseContainer.add(adresseLabel);
        adresseContainer.add(adresseArea);
        adresseContainer.add(adresseEror);

        //pour la photo
        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        String file = (String) e.getSource();
                        photoField.setText(file.substring(file.lastIndexOf('/') + 1));
                    }
                }, Display.GALLERY_IMAGE);
            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }
        });
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        photoContainer.add(photoLabel);
        photoContainer.add(photoField);
        photoContainer.add(selectPhoto);

        //le boutton valider du footer
        Button vlaiderButton = new Button("Valider");
        Container footerContainer = new Container(new BorderLayout());
        footerContainer.add(BorderLayout.CENTER, vlaiderButton);
        vlaiderButton.addActionListener((evt) -> {
            sosDisparition temps = new sosDisparition();
            // temps.setNom(titreArea.getText());
            temps.setDescription(descriptionArea.getText());
            // temps.setSexe(sexeBox.getSelectedItem().toString());
            temps.setImage(photoField.getText());
            temps.setRace(RaceArea.getText());
            temps.setAdresse(adresseArea.getText());
            temps.setLieu(lieuArea.getText());
            temps.setNum_tel(Integer.parseInt(telArea.getText()));

            temps.setDate(datepiker.getDate());
            sosservices aaaa = new sosservices();
            aaaa.ajoutTask(temps);
            Dialog.show("felicitation", " votre sos et ajouter", "ok", null);
            affichergui h = new affichergui();
            h.getHi().show();

        });
        //remplissage de la forme
        this.hi.add(titreContainer);
        this.hi.add(descriptionContainer);
        this.hi.add(lieuContainer);

        this.hi.add(adresseContainer);

        this.hi.add(dateContainer);
        this.hi.add(RaceContainer);
        //  this.hi.add(validiteContainer);
        this.hi.add(photoContainer);
        this.hi.add(footerContainer);

    }

}
