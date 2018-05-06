/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.concours;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.esprit.zanimo.Bar;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.concours;
import com.esprit.gui.home.Homegui;
import com.esprit.services.concours.concoursService;
import java.text.SimpleDateFormat;

/**
 *
 * @author angham
 */
public class AffichageConcours extends Bar {

    Label titre;
    Label type;
    Label race;
    Label capacite;
    Label datedebut;
    Label datefin;

    concoursService serviceTask = new concoursService();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public AffichageConcours() {
        super();
        this.hi.setTitle("concours list");
        //type = new Label("");
        //capacite = new Label("");
        //  race = new Label("");
        for (concours c : serviceTask.getList2()) {

            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            titre = new Label("" + c.getTitre());
            type = new Label("" + c.getType());
            race = new Label("" + c.getRace());
            capacite = new Label("" + c.getCapacite());
            String datedepstring = formater.format(c.getDateDebut());
            String datefinstring = formater.format(c.getDateFin());

            datedebut = new Label("" + datedepstring);
            datefin = new Label("" + datefinstring);

//            datedebut = new Label("" + c.getType());
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Button btSup = new Button("Supprimer");
            btSup.addActionListener((evt) -> {
                concoursService concoursService = new concoursService();
                if (Dialog.show("Supprission", "etes vs sur de suu le concours", "Supprimer", "Ann")) {
                    concoursService.supprimerConcours(c.getId());
                    Homegui temps = new Homegui();
                    temps.getHi().show();
                }
            });
            Button btnModif = new Button("Modifier");

            Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c3.add(btSup);
            c3.add(btnModif);

            c1.add(titre);
            c1.add(type);
            c1.add(race);
            c1.add(capacite);
            c1.add(datedebut);
            c1.add(datefin);

            this.hi.add(c1);
            this.hi.add(c3);
            this.hi.add(new Label("______________________________"));

        }

        //System.out.println(serviceTask.getList2().get(5).getTitre());
        //  titre.setText(serviceTask.getList2().get(0).getTitre());
        //  type.setText(serviceTask.getList2().get(0).getType());
        //race.setText(serviceTask.getList2().get(0).getRace());
        // capacite.setText(serviceTask.getList2().get(0).getCapacite());
        //  this.hi.add(type);
        //  this.hi.add(race);
        //    this.hi.add(capacite);
    }

}
