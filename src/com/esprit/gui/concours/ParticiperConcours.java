/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.concours;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.esprit.entities.User;
import com.esprit.entities.animal;
import com.esprit.entities.concours;
import com.esprit.entities.participer;
import com.esprit.services.concours.concoursService;
import com.esprit.services.concours.participationService;
import com.esprit.zanimo.Bar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angham
 */
public class ParticiperConcours extends Bar {

    Label titre;
    Label type;
    Label race;
    Label capacite;
    Label datedebut;
    Label datefin;
    ComboBox<animal> cp;
    Button btnpar;

    concoursService serviceTask = new concoursService();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public ParticiperConcours() {
        super();
        this.hi.setTitle("concours list");
        //type = new Label("");
        //capacite = new Label("");
        //  race = new Label("");
        participationService sc = new participationService();
        ArrayList<concours> temps = serviceTask.getList2();
        ArrayList<concours> temps2 = new ArrayList<>();
        for (concours c : temps) {
            if (c.getCapacite() > c.getNbparticipant()) {
                temps2.add(c);
            }
        }
        for (concours c : temps2) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            titre = new Label("" + c.getTitre());
            type = new Label("" + c.getType());
            race = new Label("" + c.getRace());
            capacite = new Label("" + c.getCapacite());
            String datedepstring = formater.format(c.getDateDebut());
            datedebut = new Label("" + datedepstring);
//            datedebut = new Label("" + c.getType());
            List<animal> ls = new ArrayList<animal>();
//            ls = sc.getList2();
            System.out.println("ccccccccccccccccccc" + User.getIdOfConnectedUser());
            for (animal l : sc.getList2()) {
                if (l.getId_membre() == User.getIdOfConnectedUser()) {
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    ls.add(l);
                }
            }
            cp = new ComboBox<>();

            DefaultListModel dd = new DefaultListModel(ls);
            cp.setModel(dd);
            btnpar = new Button("je participe");

            c1.add(titre);
            c1.add(type);
            c1.add(race);
            c1.add(capacite);
            c1.add(datedebut);
            c1.add(cp);
            c1.add(btnpar);
            this.hi.add(c1);
            this.hi.add(new Label("______________________________"));
            btnpar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    animal selectedanimal = (animal) cp.getSelectedItem();
                    if (c.getCapacite() > 0) {
                        participer p = new participer(selectedanimal.getId(), c.getId());
                        System.out.println(p);
                        participationService ser = new participationService();
                        ser.ajoutparticip(p);
                        Dialog.show("Bienvenue", "Participer", "OK", null);
                    } else {
                        Dialog.show("Stop", "Compler", "OK", null);
                    }
                }
            });

        }

    }
}
