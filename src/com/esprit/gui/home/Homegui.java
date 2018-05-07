/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.home;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.esprit.gui.users.ListeAccessoires;
import com.esprit.zanimo.Bar;

/**
 *
 * @author makni
 */
public class Homegui extends Bar {

    public Homegui() {

        super();
        ListeAccessoires sss = new ListeAccessoires();
        this.hi = sss.getHi();
    }

}
