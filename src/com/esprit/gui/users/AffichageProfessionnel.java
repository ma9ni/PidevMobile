/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.User;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;

/**
 *
 * @author makni
 */
public class AffichageProfessionnel extends Bar {

    SpanLabel lb;
    private EncodedImage enc;

    public AffichageProfessionnel() {
        super();
        this.hi.setTitle("Liste Des Professionels");
        lb = new SpanLabel("aaaaaaaaaaaaaaaaaa");

        // lb.setText(""+serviceTask.getList2().get(0).getIdAdoption());
        UsersServices serviceTask = new UsersServices();
        System.out.println("" + serviceTask.getList2().get(0).getGouvernorat());
        // lb.setText("" + serviceTask.getList2().get(0).getId());
        hi.add(lb);

        for (User t : serviceTask.getList2()) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            Image i = (URLImage.createToStorage(enc, t.getGouvernorat(), "http://localhost/pi_dev-master/web/uploads/images/" + t.getImage() + "", URLImage.RESIZE_SCALE));

            ImageViewer img2 = new ImageViewer(i.fill(120, 130));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label l = new Label(t.getGouvernorat());
            Label lnu = new Label("" + t.getNum_tel());

            C2.add(l);
            C2.add(lnu);
            C1.add(img2);
            C1.add(C2);
            C1.setLeadComponent(l);
            hi.add(C1);

        }
    }

}
