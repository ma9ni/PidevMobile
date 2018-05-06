/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeDressage;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.FicheDeDressage;
import com.esprit.entities.User;
import com.esprit.entities.animal;
import com.esprit.services.FicheDeDressage.FicheDeDressageService;
import com.esprit.services.FicheDeSoin.FicheDeSoinService;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;

/**
 *
 * @author makni
 */
public class ajouterFicheDeDressageGUI extends Bar {

    private Label specialiteLabel;
    private Label obeissanceLabel;
    private Label displineLabel;
    private Label accompagnementLabel;
    private Label interceptionLabel;
    private Label dateDebutLabel;
    private Label dateFinLabel;
    private Picker datedebut;
    private Picker datefin;
    private ComboBox cmb;
    private TextField specialiteField;
    private Label Animal;
    private Container C3;
    private Button btn;
    private Slider obeissance;
    private Slider displine;
    private Slider accompagnement;
    private Slider interception;
    FicheDeSoinService fsDeSoinService = new FicheDeSoinService();
    FicheDeDressageService ficheDeDressageService = new FicheDeDressageService();

    public ajouterFicheDeDressageGUI() {
        Container hii = new Container(new TableLayout(2, 2));
        C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        specialiteLabel = new Label("specialite");
        obeissanceLabel = new Label("obeissance");
        displineLabel = new Label("displine");
        accompagnementLabel = new Label("accompagnement");
        interceptionLabel = new Label("interception");
        dateDebutLabel = new Label("Date Debut");
        dateFinLabel = new Label("Date Fin");

        specialiteField = new TextField("", "Specialite");
        cmb = new ComboBox<>();
        ArrayList<animal> anim = new ArrayList<>();
        anim.addAll(fsDeSoinService.getListanimal2());

        for (animal object : anim) {
            cmb.addItem(object.getNom());
        }
        Animal = new Label("Animal");

        datedebut = new Picker();
        datedebut.setType(Display.PICKER_TYPE_DATE);
        datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE);
        obeissance = new Slider();
        displine = new Slider();
        accompagnement = new Slider();
        interception = new Slider();

        obeissance.setMinValue(0);
        obeissance.setMaxValue(20);
        obeissance.setEditable(true);
        obeissance.addActionListener((evt) -> {
            obeissanceLabel.setText("obeissance :" + obeissance.getProgress());
        });

        displine.setMinValue(0);
        displine.setMaxValue(20);
        displine.setEditable(true);
        displine.addActionListener((evt) -> {
            displineLabel.setText("displine :" + displine.getProgress());
        });

        accompagnement.setMinValue(0);
        accompagnement.setMaxValue(20);
        accompagnement.setEditable(true);
        accompagnement.addActionListener((evt) -> {
            accompagnementLabel.setText("accompagnement :" + accompagnement.getProgress());
        });

        interception.setMinValue(0);
        interception.setMaxValue(20);
        interception.setEditable(true);
        interception.addActionListener((evt) -> {
            interceptionLabel.setText("interception :" + interception.getProgress());
        });

        hii.add(specialiteLabel);
        hii.add(specialiteField);
        hii.add(obeissanceLabel);
        hii.add(obeissance);
        hii.add(displineLabel);
        hii.add(displine);
        hii.add(accompagnementLabel);
        hii.add(accompagnement);
        hii.add(interceptionLabel);
        hii.add(interception);
        hii.add(dateDebutLabel);
        hii.add(datedebut);
        hii.add(dateFinLabel);
        hii.add(datefin);
        hii.add(Animal);
        hii.add(cmb);
        btn = new Button("Ajouter");
        btn.addActionListener((evt) -> {
            System.out.println(obeissance.getProgress());
        });
        cmb.addActionListener((evt) -> {
            animal a = anim.get(cmb.getSelectedIndex());
            System.out.println(a);
            System.out.println(cmb.getSelectedItem());
        });

        C3.add(btn);
        btn.addActionListener((evt) -> {
            float note = (obeissance.getProgress() + interception.getProgress() + accompagnement.getProgress() + displine.getProgress()) / 4;
//            User u = new User(8);
            animal a = anim.get(cmb.getSelectedIndex());
            FicheDeDressage ficheDeDressage = new FicheDeDressage(User.getUserConncter(), specialiteField.getText(), Float.parseFloat("" + displine.getProgress()), Float.parseFloat("" + obeissance.getProgress()), Float.parseFloat("" + accompagnement.getProgress()), Float.parseFloat("" + interception.getProgress()), note, datedebut.getDate(), datefin.getDate(), a, 1);
            System.out.println("Fiche De Dressage" + ficheDeDressage);
            ficheDeDressageService.ajoutfichedeDressage(ficheDeDressage);
        });
        this.hi.add(hii);
        this.hi.add(C3);

    }

}
