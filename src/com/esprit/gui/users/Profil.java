/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Adoption;
import com.esprit.entities.User;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author user
 */
public class Profil extends Bar {

    private EncodedImage enc;

    public Profil() {
        super();
        try {
            enc = EncodedImage.create("/profil.png");
        } catch (IOException ex) {
        }

        Image i = (URLImage.createToStorage(enc, User.getUserConncter().getUsername(), "http://localhost/pi_dev-master/web/uploads/images/" + User.getUserConncter().getImage() + "", URLImage.RESIZE_SCALE));

        Container c = new Container(BoxLayout.y());

        c.add(i);

        c.add(new Label("Nom : " + User.getUserConncter().getUsername()));
        c.add(new Label("email : " + User.getUserConncter().getEmail()));

        c.add(new Label("role : " + User.getUserConncter().getRole()));
        c.add(new Label("tel : " + User.getUserConncter().getNum_tel()));
        c.add(new Label("adresse : " + User.getUserConncter().getAdresse()));
        hi.setTitle("Profil");
        hi.add(c);

        hi.show();

    }

}
