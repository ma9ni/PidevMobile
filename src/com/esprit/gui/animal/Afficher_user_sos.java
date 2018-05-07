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
import com.esprit.entities.sosDisparition;
import com.esprit.services.animal.sosservices;
import com.esprit.services.animal.sosuserservices;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author salah
 */
public class Afficher_user_sos extends Bar {

    private EncodedImage enc;

    public Afficher_user_sos() {
        super();

        Picker p = new Picker();

        sosuserservices serviceTask = new sosuserservices();
        for (sosDisparition sos : serviceTask.getList2()) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
            }

            Image i = (URLImage.createToStorage(enc, sos.getNomproprietaire(), "http://localhost/pi_dev-master/web/upload/images/" + sos.getImage() + "", URLImage.RESIZE_SCALE));
            ImageViewer img2 = new ImageViewer(i.fill(900, 500));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label Description = new Label("Description: " + sos.getDescription());
            Label Nomproprietaire = new Label("Nomproprietaire: " + sos.getNomproprietaire());
            Label Race = new Label("Race: " + sos.getRace());
            Label Lieu = new Label("Lieu: " + sos.getLieu());
            Label Num_tel = new Label("Num_tel :" + sos.getNum_tel());
            Label Adresse = new Label("Adresse :" + sos.getAdresse());
            Button btnn = new Button("Supprimer SOS");
            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this sos??", "Ok", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl(ToolsUtilities.UrlAhmedMakni + "sosdisparition/delete/"
                        + sos.getId());
                        NetworkManager.getInstance().addToQueue(req);
                        Dialog.show("Suppression", "SOS " + sos.getNomproprietaire() + " a été supprimé avec succès!", "OK", null);
                        Afficher_user_sos h = new Afficher_user_sos();
                        h.getHi().show();
                    }
                }
            });

            C1.add(img2);
            C2.add(Nomproprietaire);
            C2.add(Description);
            C2.add(Race);
            C2.add(Lieu);
            C2.add(Adresse);
            C2.add(Num_tel);
            C2.add(btnn);

//        this.hi.add(date);
            hi.add(C1);
            hi.add(C2);
        }
    }

}
