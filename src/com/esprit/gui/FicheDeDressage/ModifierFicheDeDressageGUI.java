/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeDressage;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.entities.FicheDeDressage;
import com.esprit.services.FicheDeDressage.FicheDeDressageService;

import com.esprit.zanimo.Bar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author makni
 */
public class ModifierFicheDeDressageGUI extends Bar {

    Button btn;
    Button supp;
    FicheDeDressageService dressageService = new FicheDeDressageService();

    public ModifierFicheDeDressageGUI(FicheDeDressage fsDeDressage) {

        super();
        this.hi.setTitle("Fiche de Dressage N°:" + fsDeDressage.getId());
        TextModeLayout tl = new TextModeLayout(3, 2);
        Container f = new Container(tl);

        TextComponent specialite = new TextComponent().label("specialite").multiline(true);
        TextComponent obeissance = new TextComponent().label("obeissance");
        TextComponent accompagnement = new TextComponent().label("accompagnement");
        TextComponent interception = new TextComponent().label("interception");
//        TextComponent noteTotal = new TextComponent().label("noteTotal");
        TextComponent displine = new TextComponent().label("displine");
        PickerComponent dateDebut = PickerComponent.createDate(new Date()).label("dateDebut");
        PickerComponent dateFin = PickerComponent.createDate(new Date()).label("dateFin");
        Validator val = new Validator();
        val.addConstraint(specialite, new LengthConstraint(2));
        val.addConstraint(obeissance, new NumericConstraint(true));
        val.addConstraint(accompagnement, new NumericConstraint(true));
        val.addConstraint(displine, new NumericConstraint(true));
        val.addConstraint(interception, new NumericConstraint(true));
//        val.addConstraint(noteTotal, new NumericConstraint(true));
        btn = new Button("modifier");
        supp = new Button("supprimer");
        f.add(tl.createConstraint().widthPercentage(60), specialite);
        f.add(tl.createConstraint().widthPercentage(40), obeissance);
        f.add(tl.createConstraint().widthPercentage(40), accompagnement);
        f.add(tl.createConstraint().widthPercentage(40), displine);
        f.add(tl.createConstraint().widthPercentage(40), interception);
//        f.add(tl.createConstraint().widthPercentage(40), noteTotal);
        f.add(tl.createConstraint().widthPercentage(40), dateDebut);
        f.add(tl.createConstraint().widthPercentage(40), dateFin);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        specialite.getField().setText(fsDeDressage.getSpecialite());
        obeissance.getField().setText("" + fsDeDressage.getObeissance());
        accompagnement.getField().setText("" + fsDeDressage.getAccompagnement());
        displine.getField().setText("" + fsDeDressage.getDispline());
        interception.getField().setText("" + fsDeDressage.getInterception());
//        noteTotal.getField().setText("" + fsDeDressage.getNoteTotal());
        float noteTotal = (Float.parseFloat(displine.getField().getText()) + Float.parseFloat(obeissance.getField().getText()) + Float.parseFloat(accompagnement.getField().getText()) + Float.parseFloat(interception.getField().getText())) / 4;
        dateDebut.getPicker().setDate(fsDeDressage.getDateDebut());
        dateFin.getPicker().setDate(fsDeDressage.getDateFin());
        hi.add(f);
        hi.add(btn);
        hi.add(supp);
        btn.addActionListener((evt) -> {

            FicheDeDressage fsmodifier = new FicheDeDressage(fsDeDressage.getId(), specialite.getField().getText(), Float.parseFloat(displine.getField().getText()), Float.parseFloat(obeissance.getField().getText()), Float.parseFloat(accompagnement.getField().getText()), Float.parseFloat(interception.getField().getText()), noteTotal, dateDebut.getPicker().getDate(), dateFin.getPicker().getDate());
            dressageService.modifierfichedeSoin(fsmodifier);
        });
        supp.addActionListener((evt) -> {
            dressageService.supprimerficheDeDressage(fsDeDressage);
            afficherFicheDeDressageGUI aj = new afficherFicheDeDressageGUI();
            aj.getHi().show();
        });
    }

}
