/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Accessoire;
import com.esprit.services.user.AccessoireServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class DetailProduit {

    private Form hi;

    public DetailProduit(int idProduit, String typeProdui) {//typeProduit nous informe si le produit est un acc ou nou...
        this.hi = new Form();
        /**/
        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeAccessoires temp = new ListeAccessoires();
                temp.getHi().show();
            }
        });

        /**/
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        AccessoireServices service = new AccessoireServices();
        if (typeProdui == "Accessoire") {
            //recuperation de l'accesoire a afficher
            AccessoireServices accessoireService = new AccessoireServices();
            Accessoire accessoire = accessoireService.getAccessoire(idProduit);
            //affichage de la photo
            Container photoContainer = new Container(new BorderLayout());
            photoContainer.getStyle().setBorder(Border.createLineBorder(1));
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            placeholder = URLImage.createToStorage(encImage, accessoire.getNom(), accessoire.getPhoto(), URLImage.RESIZE_SCALE);
            ImageViewer limage = new ImageViewer();
            limage.setImage(placeholder);
            photoContainer.add(BorderLayout.CENTER, limage);

            //afichage des données de l'accessoire
            Container dataContainer = new Container();
            Label prixLab = new Label("Prix : ");
            Label lePrix = new Label("" + accessoire.getPrix() + " Dt");
            Label dsc = new Label("description : ");
            SpanLabel laDescription = new SpanLabel("" + accessoire.getDescription());
            Label qteLab = new Label("Quantité dispo : ");
            Label laQte = new Label("" + accessoire.getQteStock());
            Label categorieLab = new Label("Categorie : ");
            SpanLabel laCateg = new SpanLabel("ce produit est destiné aux \n " + accessoire.getCategorie());
            Label dateLab = new Label("publier le ");
            SpanLabel laDate = new SpanLabel("" + accessoire.getDatePublication());
            dataContainer = TableLayout.encloseIn(2,
                    prixLab,
                    lePrix,
                    dsc,
                    laDescription,
                    qteLab,
                    laQte,
                    categorieLab,
                    laCateg,
                    dateLab,
                    laDate);

            //ajout a notre form
            this.hi.setTitle(accessoire.getNom());
            this.hi.add(photoContainer);
            this.hi.add(new Label(""));
            this.hi.add(dataContainer);
        } else {//C-a-d que le produit est une nourriture

        }
    }

    public Form getHi() {
        return hi;
    }

}
