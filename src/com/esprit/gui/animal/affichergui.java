package com.esprit.gui.animal;

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
import com.esprit.services.animal.animalservices;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author salah
 */
public class affichergui extends Bar {

    Label nom;
    Label lb2;
    Label race;
    Label sexe;
    Label date;

    private EncodedImage enc;

    public affichergui() {
        super();

        Picker p = new Picker();

        animalservices serviceTask = new animalservices();
        for (animal an : serviceTask.getList2()) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            Image i = (URLImage.createToStorage(enc, an.getNom(), "http://localhost/pi_dev-master/web/upload/images/" + an.getImage() + "", URLImage.RESIZE_SCALE));
            ImageViewer img2 = new ImageViewer(i.fill(1400, 500));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            //date.setText(serviceTask.getList2().get(0).getDatedenaissance());
            Label Nom = new Label("Nom: " + an.getNom());
            Label Nomproprietaire = new Label("Nomproprietaire: " + an.getNomproprietaire());
            Label Description = new Label("Description: " + an.getDescription());
            Label Race = new Label("Race: " + an.getRace());
            Label Sexe = new Label("Sexe: " + an.getSexe());
            Button btnn = new Button("Supprimer animal");
            /* btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this sos??", "Ok", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl("http://127.0.0.1/Symfony/pi_dev/web/app_dev.php/zbotrech/"
                                + an.getId());
                        NetworkManager.getInstance().addToQueue(req);
                        Dialog.show("Suppression", "SOS " + an.getNom() + " a été supprimé avec succès!", "OK", null);
                        affichersos h = new affichersos();
                        h.getHi().show();
                    }
                }
            });*/
            C1.add(img2);
            C2.add(Nom);
            C2.add(Nomproprietaire);
            C2.add(Description);
            C2.add(Race);
            C2.add(Sexe);
            //  C2.add(btnn);

//        this.hi.add(date);
            hi.add(C1);
            hi.add(C2);
        }
    }
}
