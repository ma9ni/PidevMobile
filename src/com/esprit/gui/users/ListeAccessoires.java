/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.esprit.entities.Accessoire;
import com.esprit.services.user.AccessoireServices;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ahmed
 */
public class ListeAccessoires extends Bar {

    public ListeAccessoires() {
        super();
        AccessoireServices accessoireService = new AccessoireServices();
        this.hi.setTitle("Produits");
        //pour la barre rechercher
        Container rechercheContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField rechercheArea = new TextField("", "Rechercher", 12, TextArea.ANY);
        rechercheArea.getUnselectedStyle().setBorder(
                RoundBorder.create().rectangle(true)
        );
        rechercheContainer.add(rechercheArea);
        this.hi.add(rechercheContainer);
        //pour inverser l'ordre de la liste des produit qu'on a recuperer
        ArrayList<Accessoire> temps = accessoireService.getList2();
        Collections.reverse(temps);

        //mettre chaque  produit dans un containers et le placer danns la forme
        for (Accessoire accessoire : temps) {
            Container annonceContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            // pour l'entete de l'annonce
            Container enteteContainer = new Container(new BorderLayout());
            Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
            Label titreLabel = new Label(accessoire.getNom());
            Container prixcontainer = new Container();
            Label prixLabel = new Label("" + accessoire.getPrix() + " DT");
            prixLabel.getAllStyles().setFgColor(0xf4ad42);
//            prixcontainer.getStyle().setFont(smallBoldSystemFont);
            prixcontainer.add(prixLabel);
            enteteContainer.add(BorderLayout.WEST, titreLabel);
            enteteContainer.add(BorderLayout.EAST, prixcontainer);
            // pour la photo
            Container photoContainer = new Container(new BorderLayout());
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            placeholder = URLImage.createToStorage(encImage, accessoire.getNom(), accessoire.getPhoto(), URLImage.RESIZE_SCALE);
            ImageViewer limage = new ImageViewer();
            limage.setImage(placeholder);
            photoContainer.add(BorderLayout.CENTER, limage);
            //pour la description
            SpanLabel DescriptionLabel = new SpanLabel(accessoire.getDescription());

            //pour le bouton visualiser
            Container footerContainer = new Container(new BorderLayout());
            Button consulterBtn = new Button("Consulter");
            consulterBtn.getAllStyles().setBorder(Border.createEmpty());
            consulterBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            consulterBtn.addActionListener((evt) -> {
                DetailProduit passageform = new DetailProduit(accessoire.getId(), "Accessoire");
                passageform.getHi().show();
            });
            footerContainer.add(BorderLayout.EAST, consulterBtn);

            annonceContainer.add(enteteContainer);
            annonceContainer.add(photoContainer);
            annonceContainer.add(DescriptionLabel);
            annonceContainer.add(footerContainer);

            annonceContainer.getStyle().setBorder(Border.createLineBorder(1));
            int containerWidth = Display.getInstance().getDisplayWidth();
            annonceContainer.setWidth(containerWidth);
            this.hi.add(annonceContainer);
        }
    }

}
