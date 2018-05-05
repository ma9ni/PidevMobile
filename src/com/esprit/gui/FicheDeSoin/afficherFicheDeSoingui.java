/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeSoin;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.FicheDeSoin;
import com.esprit.gui.users.AfficherUserGui;
import com.esprit.services.FicheDeSoin.FicheDeSoinService;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author makni
 */
public class afficherFicheDeSoingui extends Bar {

    private SpanLabel lb;
    private EncodedImage enc;

    public afficherFicheDeSoingui() {

        super();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        FicheDeSoinService serviceTask = new FicheDeSoinService();
        this.hi.setTitle("Mes Fiches de Soin");
        Container hii = new Container(new TableLayout(2, 2));
        for (FicheDeSoin t : serviceTask.getList2()) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            Image i = (URLImage.createToStorage(enc, t.getId_animal().getNom(), "http://localhost/pi_dev-master/web/upload/images/" + t.getId_animal().getImage() + "", URLImage.RESIZE_SCALE));

            ImageViewer img2 = new ImageViewer(i.fill(120, 130));

            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label l = new Label("Medicament : " + t.getMedicament());
            Label nomanim = new Label("animal : " + t.getId_animal().getNom());
            Label lnu = new Label("Observation : " + t.getObservation());

            String datdebu = formater.format(t.getProchainRDV());
            String datcreat = formater.format(t.getDateCreation());
            Label prch = new Label("Prochain RDV : Le " + datdebu);
            Label datecr = new Label("Date De creation : Le " + datcreat);
            C1.add(img2);
            C2.add(nomanim);
            C2.add(l);
            C2.add(lnu);
            C2.add(datecr);
            C2.add(prch);
            C1.add(C2);
            C1.setLeadComponent(l);
            hi.add(C1);
            l.addPointerPressedListener((evt) -> {
                ModifierFicheDeSoingui aj = new ModifierFicheDeSoingui(t);
                aj.getHi().show();
            });
        }
        this.hi.getToolbar().addCommandToOverflowMenu("Ajouter Fiche De Soin ", null, (evt) -> {
            ajouterFicheDeSoin aj = new ajouterFicheDeSoin();
            aj.getHi().show();
//            aj.hii.show();
        });

        this.hi.getToolbar().addCommandToOverflowMenu("Modifer Fiche De Soin ", null, (evt) -> {
//            ModifierFicheDeSoingui aj = new ModifierFicheDeSoingui();
//            aj.getHi().show();
//            aj.hii.show();
        });

//        lb = new SpanLabel();
        //        // lb = new SpanLabel("aaaaaaahmeeeedaaaaaa");
        //        lb.setText("Animal :" + serviceTask.getList2().get(0).getId_animal().getNom());
        //        hi.add(lb);
        System.out.println("" + serviceTask.getList2().get(0).getId_animal().getNom());

        /*/*/    }

}
