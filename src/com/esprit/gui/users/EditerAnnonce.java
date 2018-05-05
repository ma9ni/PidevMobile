/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Accessoire;
import com.esprit.entities.Categorie;
import com.esprit.services.user.AccessoireServices;
import com.esprit.services.user.CategorieServices;
import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class EditerAnnonce {

    private Form hi;
    public Label titreEror;
    public Label prixEror;

    public EditerAnnonce(int idProduit) {
        this.hi = new Form();
        /**/
        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar(new Command("Retour") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GererMesAnnonces temp = new GererMesAnnonces();
                temp.getHi().show();
            }
        });
        /**/
        //affichage de la photo
        AccessoireServices accessoireService = new AccessoireServices();
        Accessoire accessoire = accessoireService.getAccessoire(idProduit);

        //la photo
        Container photoContainer = new Container(new BorderLayout());
        int deviceWidth = Display.getInstance().getDisplayWidth();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        placeholder = URLImage.createToStorage(encImage, accessoire.getNom(), accessoire.getPhoto(), URLImage.RESIZE_SCALE);
        ImageViewer limage = new ImageViewer();
        limage.setImage(placeholder);
        photoContainer.add(BorderLayout.CENTER, limage);

        //le titre
        Label titreLabel = new Label("Titre");
        TextField titreArea = new TextField("", "", 12, TextArea.ANY);
        titreArea.setText(accessoire.getNom());
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
        descriptionArea.setText(accessoire.getDescription());
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);
        descriptionContainer.add(descriptionArea);

        //pour le prix de l'accesoire
        Label prixLabel = new Label("Prix");
        TextField prixArea = new TextField("", "", 12, TextArea.NUMERIC);
        prixArea.setText("" + accessoire.getPrix());
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
        categorieBox.setSelectedItem(accessoire.getCategorie());
        Container categorieContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        categorieContainer.add(CategorieLabel);
        categorieContainer.add(categorieBox);

        //pour la qutantité
        Label stockLabel = new Label("Votre sotcke");
        String[] lastock = {"1 unité ", "moin de 10", "plus de 10"};
        ComboBox stockBox = new ComboBox();
        for (int i = 0; i < 3; i++) {
            stockBox.addItem(lastock[i]);
        }
        stockBox.setSelectedItem(accessoire.getQteStock());
        Container stockContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        stockContainer.add(stockLabel);
        stockContainer.add(stockBox);
        Button vlaiderButton = new Button("Valider");
        Container footerContainer = new Container(new BorderLayout());
        footerContainer.add(BorderLayout.CENTER, vlaiderButton);
        vlaiderButton.addActionListener((evt) -> {
            accessoire.setNom(titreArea.getText());
            accessoire.setDescription(descriptionArea.getText());
            accessoire.setPrix(Float.parseFloat(prixArea.getText()));
            accessoire.setCategorie(categorieBox.getSelectedItem().toString());
            accessoire.setQteStock(stockBox.getSelectedItem().toString());
            AccessoireServices service = new AccessoireServices();
            service.modifierAccessoire(accessoire);
            Dialog.show("felicitation", " votre annonce est modifier", "ok", null);
            GererMesAnnonces h = new GererMesAnnonces();
            h.getHi().show();
        });

        //ajout des differents element a notre forme
        this.hi.add(photoContainer);
        this.hi.add(titreContainer);
        this.hi.add(descriptionContainer);
        this.hi.add(prixContainer);
        this.hi.add(categorieContainer);
        this.hi.add(stockContainer);
        this.hi.add(footerContainer);
    }

    public Form getHi() {
        return hi;
    }
}
