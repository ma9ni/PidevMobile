/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Accessoire;
import com.esprit.entities.Categorie;
import com.esprit.entities.User;
import com.esprit.gui.home.Homegui;
import com.esprit.services.user.AccessoireServices;
import com.esprit.services.user.CategorieServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Ahmed
 */
public class AjouterAccessoire extends Bar {

    private Label titreEror;
    private Label prixEror;
    private Label categorieEror;
    public static String pphoto = "";

    public AjouterAccessoire() {
        super();
        this.hi.setTitle("Publier une annonce");
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
        Label descriptionEror = new Label("*");
        descriptionEror.getAllStyles().setFgColor(0xff0000);
        descriptionEror.setVisible(false);
        Container hihou = new Container(new BoxLayout(BoxLayout.X_AXIS));
        hihou.addAll(descriptionLabel, descriptionEror);
        descriptionContainer.add(hihou);
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
        Label CategorieLabel = new Label("Categorie");
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
        categorieContainer.add(categorieEror);

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
        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            /*if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        String file = (String) e.getSource();
                        pphoto = file.substring(file.lastIndexOf('/') + 1);
                        photoField.setText(file.substring(file.lastIndexOf('/') + 1));
                    }
                }, Display.GALLERY_IMAGE);
            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }*/

            Display.getInstance().openGallery(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    if (evt == null) {
                        return;
                    }
                    String filename = (String) evt.getSource();

                    if (Dialog.show("Send file?", filename, "OK", "Cancel")) {
                        MultipartRequest req = new MultipartRequest();
                        String endpoint = "http://localhost/pi_dev-master/web/app_dev.php/api/uploadphoto/";
                        System.out.println("endpoint  : " + endpoint);
                        req.setUrl(endpoint);
                        req.addArgument("message", "test");
                        InputStream is = null;
                        try {
                            is = FileSystemStorage.getInstance().openInputStream(filename);
                            req.addData("file", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
                            req.setFilename("file", filename);//any unique name you want
                            req.addResponseListener(new ActionListener<NetworkEvent>() {
                                @Override
                                public void actionPerformed(NetworkEvent evt) {
                                    byte[] data = (byte[]) req.getResponseData();
                                    String s = new String(data);
                                    if (s.equals("ok")) {
// entité = nom de la photo
                                        System.out.println("SUCCESS");
                                        String newfilename = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
                                        pphoto = filename.substring(filename.lastIndexOf('/') + 1);
                                        photoField.setText("http://localhost/pi_dev-master/web/uploads/images" + filename.substring(filename.lastIndexOf('/') + 1));
                                        // photoField.setText(filename);
                                        pphoto = (newfilename);
                                    }
                                }
                            });
                            NetworkManager.getInstance().addToQueue(req);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

            }, Display.GALLERY_IMAGE);

        });
        //-----------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------
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

        //pour les message d'erreur qui sera afiicher en bas de page
        Container stockEtErroContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        stockEtErroContainer.add(stockContainer);
        //le boutton valider du footer
        Button vlaiderButton = new Button("Valider");
        Container footerContainer = new Container(new BorderLayout());
        footerContainer.add(BorderLayout.CENTER, vlaiderButton);
        vlaiderButton.addActionListener((evt) -> {
            String test = "";
            if (titreArea.getText() == "") {
                titreEror.setVisible(true);
                test = test + "t";
            }
            if (descriptionArea.getText() == "") {
                descriptionEror.setVisible(true);
                test = test + "d";
            }
            try {
                Float ppp = Float.parseFloat(prixArea.getText());
            } catch (Exception e) {
                prixEror.setVisible(true);
                test = test + "p";
            }
            if (test == "") {

                Accessoire accessoire = new Accessoire();
                accessoire.setNom(titreArea.getText());
                accessoire.setDescription(descriptionArea.getText());
                accessoire.setPrix(Float.parseFloat(prixArea.getText()));
                accessoire.setCategorie(categorieBox.getSelectedItem().toString());
                accessoire.setValiditePublication(validiteeBox.getSelectedItem().toString());
                accessoire.setQteStock(stockBox.getSelectedItem().toString());
                accessoire.setPhoto(photoField.getText());
                accessoire.setIdMembre(User.getUserConncter().getId());
                AccessoireServices service = new AccessoireServices();
                service.ajouterAccessoire(accessoire);
                Dialog.show("felicitation", " votre annonce est partagé", "ok", null);
                ListeAccessoires h = new ListeAccessoires();
                h.getHi().show();
            } else {
                Label ligne1Eror = new Label();
                String etror = "Verifier les champs avec '*' ";
                if (test.indexOf("t") > -1) {
                    etror = etror + "|Titre";
                }
                if (test.indexOf("p") > -1) {
                    etror = etror + "|Prix";
                }
                if (test.indexOf("d") > -1) {
                    etror = etror + "|Description";
                }
                etror = etror + "|";
                Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
                ligne1Eror.setText(etror);
                ligne1Eror.getAllStyles().setFgColor(0xff0000);
                ligne1Eror.getAllStyles().setFont(smallBoldSystemFont);
                stockEtErroContainer.add(ligne1Eror);
            }
            this.hi.refreshTheme();
        });
        //remplissage de la forme
        this.hi.add(titreContainer);
        this.hi.add(descriptionContainer);
        this.hi.add(prixContainer);
        this.hi.add(categorieContainer);
        this.hi.add(validiteContainer);
        this.hi.add(photoContainer);
        this.hi.add(stockEtErroContainer);
        this.hi.add(footerContainer);

    }
}
