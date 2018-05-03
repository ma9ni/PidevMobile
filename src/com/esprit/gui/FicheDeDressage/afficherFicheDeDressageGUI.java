/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeDressage;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.FicheDeDressage;
import com.esprit.services.FicheDeDressage.FicheDeDressageService;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author makni
 */
public class afficherFicheDeDressageGUI extends Bar {

    private EncodedImage enc;

    public afficherFicheDeDressageGUI() {

        super();
        this.hi.setTitle("mes Fiche de Dressage");

        FicheDeDressageService serviceTask = new FicheDeDressageService();

        Container hii = new Container(new TableLayout(2, 2));
        for (FicheDeDressage t : serviceTask.getList2FicheDeDressages()) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            Image i = (URLImage.createToStorage(enc, t.getId_animal().getNom(), "http://localhost/pi_dev-master/web/upload/images/" + t.getId_animal().getImage() + "", URLImage.RESIZE_SCALE));

            ImageViewer img2 = new ImageViewer(i.fill(120, 130));

            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

            Label l = new Label("Specialite : " + t.getSpecialite());
            Label nomanim = new Label("animal : " + t.getId_animal().getNom());
            Label Displine = new Label("Displine : " + t.getDispline());
            Label Interception = new Label("Interception : " + t.getInterception());
            Label Obeissance = new Label("Obeissance : " + t.getObeissance());
            Label NoteTotal = new Label("Note Total : " + t.getNoteTotal());

            String datdebu = formater.format(t.getDateDebut());
            String datfin = formater.format(t.getDateFin());
            Label prch = new Label("Date Debut :  " + datdebu);
            Label datecr = new Label("Date De Fin  " + datfin);

            C1.add(img2);
            C2.add(nomanim);
            C2.add(l);
            C2.add(Displine);
            C2.add(Interception);
            C2.add(Obeissance);
            C2.add(NoteTotal);
            C2.add(datecr);
            C2.add(prch);
            C1.add(C2);

            C1.setLeadComponent(l);
            hi.add(C1);
            l.addPointerReleasedListener((evt) -> {
                ModifierFicheDeDressage aj = new ModifierFicheDeDressage(t);
                aj.getHi().show();
            });
        }

    }

}
