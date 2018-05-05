/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.entities.Accessoire;
import com.esprit.services.user.AccessoireServices;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class GererMesAnnonces extends Bar {

    public GererMesAnnonces() {
        super();
        this.hi.setTitle("Mes Annonces");
        AccessoireServices accessoireService = new AccessoireServices();
        ArrayList<Accessoire> listeAccessoire = new ArrayList<>();
        listeAccessoire = accessoireService.mesAccessoires(1);
        for (Accessoire accessoire : listeAccessoire) {
            //pour la photo de l'accessoire

            Container photoContainer = new Container(new BorderLayout());
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            placeholder = URLImage.createToStorage(encImage, accessoire.getNom(), accessoire.getPhoto(), URLImage.RESIZE_SCALE);
            ImageViewer limage = new ImageViewer();
            limage.setImage(placeholder);
            photoContainer.add(BorderLayout.CENTER, limage);

            //pour les donnee
            Container donneeContainer = new Container(new BorderLayout());
            Container tempContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nomLab = new Label(accessoire.getNom());
            SpanLabel descripLab = new SpanLabel(accessoire.getDescription());
            tempContainer.addAll(nomLab, descripLab);

            //pour boutton de chaque annonces
            Container boutonBar = new Container(new BorderLayout());
            Button consulterBtn = new Button("Editer");
            consulterBtn.getAllStyles().setBorder(Border.createEmpty());
            consulterBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            consulterBtn.addActionListener((evt) -> {
                EditerAnnonce passageform = new EditerAnnonce(accessoire.getId());
                passageform.getHi().show();
            });
            Button supprimerBtn = new Button("Supprimer");
            supprimerBtn.getAllStyles().setBorder(Border.createEmpty());
            supprimerBtn.getAllStyles().setFgColor(0xff0000);
            supprimerBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            supprimerBtn.addActionListener((evt) -> {
                if (Dialog.show("Confirmer!", "etes vous sur de vouloir supprimer ", "Supprimer", "Annuler") == true) {
                    supprimeraccessoire(accessoire.getId());

                }
            });
            boutonBar.add(BorderLayout.EAST, consulterBtn);
            boutonBar.add(BorderLayout.WEST, supprimerBtn);

            donneeContainer.add(BorderLayout.WEST, tempContainer);
            donneeContainer.add(BorderLayout.SOUTH, boutonBar);

            Container accessoireContainer = new Container(new BorderLayout());
            accessoireContainer.add(BorderLayout.WEST, photoContainer);
            accessoireContainer.add(BorderLayout.EAST, donneeContainer);
            accessoireContainer.getStyle().setBorder(Border.createLineBorder(1));
            int containerWidth = Display.getInstance().getDisplayWidth();
            accessoireContainer.setWidth(containerWidth);
            this.hi.add(accessoireContainer);

        }

    }

    public void supprimeraccessoire(int id) {
        AccessoireServices accessoireService = new AccessoireServices();
        accessoireService.supprimerAccessoire(id);
        GererMesAnnonces tempss = new GererMesAnnonces();
        tempss.getHi().show();

    }

}
