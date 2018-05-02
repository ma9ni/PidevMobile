/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanLabel;
import com.esprit.zanimo.Bar;

/**
 *
 * @author makni
 */
public class FindUsergui extends Bar {

    SpanLabel lb;

    public FindUsergui() {
        AffichageProfessionnel afp = new AffichageProfessionnel();

//        afp.aff(listUsers);
        lb = new SpanLabel("ahahah");
        this.hi.add(lb);

    }

}
