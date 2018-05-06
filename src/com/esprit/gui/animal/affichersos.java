/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.animal;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.sosDisparition;
import static com.esprit.gui.animal.afficher_user_animals.ani;
import com.esprit.services.animal.sosservices;

import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salah
 */
public class affichersos extends Bar {

    private EncodedImage enc;

    public affichersos() {
        super();
        TextField recherche = new TextField();
        this.hi.add(recherche);
        recherche.addActionListener((evt) -> {
            String unerecherche = recherche.getText();
            ArrayList<sosDisparition> recherchelist = new ArrayList<>();
            sosservices serviceTask1 = new sosservices();

            ArrayList<sosDisparition> temp = serviceTask1.getList2();
            for (sosDisparition sss : temp) {
                if (sss.getAdresse().indexOf(unerecherche) > -1) {
                    recherchelist.add(sss);
                }

            }
            if (recherche.getText() != "") {
                this.hi.removeAll();
                this.hi.refreshTheme();
                for (sosDisparition sos : recherchelist) {
                    ajouterBoucle(sos);

                }
            }

        });
        Picker p = new Picker();

        sosservices serviceTask = new sosservices();

        for (sosDisparition sos : serviceTask.getList2()) {
            ajouterBoucle(sos);

        }
    }

    public void ajouterBoucle(sosDisparition sos) {

        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
        }

        Image i = (URLImage.createToStorage(enc, sos.getNomproprietaire(), "http://localhost/Symfony/pi_dev/web/upload/images/" + sos.getImage() + "", URLImage.RESIZE_SCALE));
        ImageViewer img2 = new ImageViewer(i.fill(900, 500));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label Description = new Label("Description: " + sos.getDescription());
        Label Nomproprietaire = new Label("Nomproprietaire: " + sos.getNomproprietaire());
        Label Race = new Label("Race: " + sos.getRace());
        Label Lieu = new Label("Lieu: " + sos.getLieu());
        Label Num_tel = new Label("Num_tel :" + sos.getNum_tel());
        Label Adresse = new Label("Adresse :" + sos.getAdresse());
        Button Contact = new Button("Contact");
        Contact.addActionListener((evt) -> {
            Message m = new Message("Body of message");
            Display.getInstance().sendMessage(new String[]{sos.getId_membre().getEmail()}, "Subject of message", m);
        });
        C1.add(img2);
        C2.add(Nomproprietaire);
        C2.add(Description);
        C2.add(Race);
        C2.add(Lieu);
        C2.add(Adresse);
        C2.add(Num_tel);
        Container c3 = new Container();
        c3.add(Contact);

        C2.setLeadComponent(Nomproprietaire);
        Nomproprietaire.addPointerReleasedListener((evt) -> {
            //   System.out.println("444444444444444444444");
            sosmap ma = new sosmap(sos);

            ma.getHi().show();
        });

//        this.hi.add(date);
        hi.add(C1);
        hi.add(C2);
        hi.add(c3);

    }

}
