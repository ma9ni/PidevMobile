/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Accessoire;
import com.esprit.entities.User;
import com.esprit.services.user.AccessoireServices;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
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
            this.hi.setTitle(accessoire.getNom());
            this.hi.add(photoContainer);
            this.hi.add(new Label(""));
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
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String reportDate = df.format(accessoire.getDatePublication());
            SpanLabel laDate = new SpanLabel("" + reportDate);
            //ce ci est pour le footer qui va contenir les coordonner de l'annonceur ainsi que publier sur fb
            Container footerContainer = new Container(new BorderLayout());
            Button contacterAnnonceur = new Button("Contatcter l'annonceur");
            contacterAnnonceur.addActionListener((evt) -> {
                this.hi.refreshTheme();
                contacterAnnonceur.setVisible(false);
                User temps = accessoireService.getAnnonceur(accessoire.getIdMembre());
                SpanLabel test = new SpanLabel(temps.getUsername());
                footerContainer.add(BorderLayout.WEST, new Label("Adresse Email :"));
                footerContainer.add(BorderLayout.EAST, new Label(temps.getEmail()));
            });
            this.hi.addAll(prixLab,
                    lePrix,
                    dsc,
                    laDescription,
                    qteLab,
                    laQte,
                    categorieLab,
                    laCateg,
                    dateLab,
                    laDate,
                    contacterAnnonceur
            );
            this.hi.add(footerContainer);

            //pour le partage facebook
            Button partagerBtn = new Button("Partager");
            partagerBtn.addActionListener((evt) -> {
                System.err.println("-_-_-_-_-__-_-_-_- : fbpartage");
                String accessToken = "EAACEdEose0cBAMUZA2KXwDvG8M6h3Cijbw5ZCOax3e3PN4Lu9VoMeSl404xxy2vYywBqbYu6PbqIPGZAkDEPYHONenVcW4VJi92R0VoPKZBLOATIcZA6QGPbKLpWKOSmWes12FxvxmBBh6pKzZAVOFB4Lkra3ZBjoBEGK5TjnUay9Ut3tDRTanK7qSgZAGTqYl290AVanOOqQzAZBs9PQwfao";
                FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_0);
                FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", "Accessoire : \n  " + accessoire.toString()));
                System.out.println("fb.com/" + response.getId());
                Dialog.show("Succes", "Votre Article à été partagé sur facebook", "Fermer", null);

            });
            this.hi.add(partagerBtn);
            this.hi.add(new Label("_________________________"));
        }
    }

    public Form getHi() {
        return hi;
    }

}
