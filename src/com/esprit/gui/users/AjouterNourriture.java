/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.*;
import com.esprit.gui.home.Homegui;
import static com.esprit.gui.users.AjouterAccessoire.pphoto;
import com.esprit.services.user.AccessoireServices;
import com.esprit.services.user.CategorieServices;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class AjouterNourriture extends Bar {

    private Label titreEror;
    private Label prixEror;

    public AjouterNourriture() {
        super();
        this.hi.setTitle("Publier une annonce pour nourriture");
        //pour le titre de l'accessoire
        Label titreLabel = new Label("Titre");
        TextField titreArea = new TextField("", "Titre", 12, TextArea.ANY);
        titreEror = new Label("*");
        titreEror.getAllStyles().setFgColor(0xff0000);
        titreEror.setVisible(false);
        Container titreContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        titreContainer.add(titreLabel);
        titreContainer.add(titreArea);
        titreContainer.add(titreEror);

        //pour la description de l'accesoire
        Label descriptionLabel = new Label("Description : ");
        TextArea descriptionArea = new TextArea(3, 20);
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);
        descriptionContainer.add(descriptionArea);

        //pour le prix de l'accesoire
        Label prixLabel = new Label("Prix");
        TextField prixArea = new TextField("", "prix", 12, TextArea.NUMERIC);
        prixEror = new Label("*");
        prixEror.getAllStyles().setFgColor(0xff0000);
        prixEror.setVisible(false);
        Container prixContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        prixContainer.add(prixLabel);
        prixContainer.add(prixArea);
        prixContainer.add(prixEror);

        //pour la categorie
        /*Label CategorieLabel = new Label("Categorie");
        CategorieServices serviceTask = new CategorieServices();
        ArrayList<Categorie> lescategories = serviceTask.getList2();
        ComboBox categorieBox = new ComboBox();
        for (Categorie temp : lescategories) {
            categorieBox.addItem(temp.getNom());
        }
        categorieEror = new Label("*");
        categorieEror.getAllStyles().setFgColor(0xff0000);
        categorieEror.setVisible(false);
        Container categorieContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        categorieContainer.add(CategorieLabel);
        categorieContainer.add(categorieBox);
        categorieContainer.add(categorieEror);*/
        //pour la validité
        Label validiteLabel = new Label("Validité");
        String[] lesValidite = {"1 semaine", "2 semaine", "1 mois", "3 mois"};
        ComboBox validiteeBox = new ComboBox();
        for (int i = 0; i < 4; i++) {
            validiteeBox.addItem(lesValidite[i]);
        }
        Container validiteContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        validiteContainer.add(validiteLabel);
        validiteContainer.add(validiteeBox);

        //pour la photo
        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
//        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        String file = (String) e.getSource();
                        pphoto = file.substring(file.lastIndexOf('/') + 1);
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

        //pour la qutantité
        Label stockLabel = new Label("Votre sotcke");
        String[] lastock = {"1 unité ", "moin de 10", "plus de 10"};
        ComboBox stockBox = new ComboBox();
        for (int i = 0; i < 3; i++) {
            stockBox.addItem(lastock[i]);
        }
        Container stockContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        stockContainer.add(stockLabel);
        stockContainer.add(stockBox);

        //le boutton valider du footer
        Button vlaiderButton = new Button("Valider");
        Container footerContainer = new Container(new BorderLayout());
        footerContainer.add(BorderLayout.CENTER, vlaiderButton);
        vlaiderButton.addActionListener((evt) -> {
            Nourriture temp = new Nourriture();
            /**/
            temp.setCategorie(pphoto);
            /**/
            Dialog.show("felicitation", " votre annonce est partagé", "ok", null);
            Homegui h = new Homegui();
            h.getHi().show();
        });
        //remplissage de la forme
        this.hi.add(titreContainer);
        this.hi.add(descriptionContainer);
        this.hi.add(prixContainer);
        this.hi.add(validiteContainer);
        this.hi.add(photoContainer);
        this.hi.add(stockContainer);
        this.hi.add(footerContainer);

    }

}
