/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.animal;

import Utilities.ToolsUtilities;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.animal;
import com.esprit.services.animal.animalsusersevices;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author salah
 */
public class afficher_user_animals extends Bar {

    Label nom;
    Label nomproprietaire;
    Label lb2;
    Label race;
    Label sexe;
    Label date;
    static int ani;
    private EncodedImage enc;

    public afficher_user_animals() {
        super();

        Picker p = new Picker();

        animalsusersevices serviceTask = new animalsusersevices();
        for (animal an : serviceTask.getList2()) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            Image i = (URLImage.createToStorage(enc, an.getNom(), "http://localhost/pi_dev-master/web/upload/images/" + an.getImage() + "", URLImage.RESIZE_SCALE));
            ImageViewer img2 = new ImageViewer(i.fill(1400, 600));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            //date.setText(serviceTask.getList2().get(0).getDatedenaissance());
            Label Nom = new Label("Nom: " + an.getNom());
            Label Nomproprietaire = new Label("Nomproprietaire: " + an.getNomproprietaire());
            Label Description = new Label("Description: " + an.getDescription());
            Label Race = new Label("Race: " + an.getRace());
            Label Sexe = new Label("Sexe: " + an.getSexe());
            Button btnn = new Button("Supprimer animal");
            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this sos??", "Ok", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl(ToolsUtilities.UrlAhmedMakni + "/zbotrech/"
                        + an.getId());
                        NetworkManager.getInstance().addToQueue(req);
                        Dialog.show("Suppression", "Animal " + an.getNom() + " a été supprimé avec succès!", "OK", null);
                        afficher_user_animals h = new afficher_user_animals();
                        h.getHi().show();
                    }
                }
            });

            C1.add(img2);
            C2.add(Nom);
            C2.add(Nomproprietaire);
            C2.add(Description);
            C2.add(Race);
            C2.add(Sexe);
            C2.add(btnn);
            C2.setLeadComponent(Nom);
            Nom.addPointerReleasedListener((evt) -> {
                System.out.println("444444444444444444444");
                modifier_animal ma = new modifier_animal(an);
//                ani = an;  System.out.println("aniiii" + ani);
                ani = an.getId();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                System.out.println("aniiii" + ani);
                ma.getHi().show();
            });

//        this.hi.add(date);
            hi.add(C1);
            hi.add(C2);
        }
    }

}
