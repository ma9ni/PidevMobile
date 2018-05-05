/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeSoin;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.entities.FicheDeSoin;
import com.esprit.services.FicheDeSoin.FicheDeSoinService;
import com.esprit.zanimo.Bar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author makni
 */
public class ModifierFicheDeSoingui extends Bar {

    Button btn;
    Button supp;
    FicheDeSoinService fsDeSoinService = new FicheDeSoinService();

    public ModifierFicheDeSoingui(FicheDeSoin fs) {
        super();

        TextModeLayout tl = new TextModeLayout(3, 2);
        Container f = new Container(tl);

        TextComponent observation = new TextComponent().label("observation").multiline(true);
        TextComponent price = new TextComponent().label("Price");
        TextComponent medicament = new TextComponent().label("medicament").errorMessage("price is must be number");
        PickerComponent prochainRDV = PickerComponent.createDate(new Date()).label("prochainRDV");
//        TextComponent description = new TextComponent().label("Description").multiline(true);

        Validator val = new Validator();
        val.addConstraint(observation, new LengthConstraint(60));
        val.addConstraint(price, new NumericConstraint(true));
        btn = new Button("modifier");
        supp = new Button("suprrimer");
        f.add(tl.createConstraint().widthPercentage(60), observation);

        f.add(tl.createConstraint().widthPercentage(40), prochainRDV);

        f.add(medicament);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//        f.add(price);
//        f.add(tl.createConstraint().horizontalSpan(2), description);
        observation.getField().setText(fs.getObservation());
        medicament.getField().setText(fs.getMedicament());
        prochainRDV.getPicker().setDate(fs.getProchainRDV());

        hi.add(f);
        hi.add(btn);
        hi.add(supp);
        btn.addActionListener((evt) -> {
            FicheDeSoin fsmodi = new FicheDeSoin(fs.getId(), observation.getField().getText(), medicament.getField().getText(), prochainRDV.getPicker().getDate());
            fsDeSoinService.modifierfichedeSoin(fsmodi);
            System.out.println("OOOOOK");
            System.out.println(fsmodi);
        });
        supp.addActionListener((evt) -> {
            fsDeSoinService.supprimerficheDeDressage(fs);
            afficherFicheDeSoingui aj = new afficherFicheDeSoingui();
            aj.getHi().show();
        });
//        System.out.println(date.getPicker().getDate());
////        title.getField().setText("aaaa");
//
//        hi.setEditOnShow(title.getField());
    }

}
