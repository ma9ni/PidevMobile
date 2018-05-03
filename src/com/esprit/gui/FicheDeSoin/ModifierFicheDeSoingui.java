/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeSoin;

import com.codename1.ui.Container;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.zanimo.Bar;
import java.util.Date;

/**
 *
 * @author makni
 */
public class ModifierFicheDeSoingui extends Bar {

    public ModifierFicheDeSoingui() {
        super();

        TextModeLayout tl = new TextModeLayout(3, 2);
        Container f = new Container(tl);

        TextComponent title = new TextComponent().label("Title");
        TextComponent price = new TextComponent().label("Price");
        TextComponent location = new TextComponent().label("Location");
        PickerComponent date = PickerComponent.createDate(new Date()).label("Date");
        TextComponent description = new TextComponent().label("Description").multiline(true);

        Validator val = new Validator();
        val.addConstraint(title, new LengthConstraint(2));
        val.addConstraint(price, new NumericConstraint(true));

        f.add(tl.createConstraint().widthPercentage(60), title);

        f.add(tl.createConstraint().widthPercentage(40), date);

        f.add(location);
        f.add(price);
        f.add(tl.createConstraint().horizontalSpan(2), description);

        hi.add(f);

        System.out.println(date.getPicker().getDate());
//        title.getField().setText("aaaa");

        hi.setEditOnShow(title.getField());
    }

}
