/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.User;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author makni
 */
public class AffichageProfessionnel extends Bar {

    SpanLabel lb;
    private EncodedImage enc;
    public static int idUserstatic;
    private ComboBox cmb;
    private Button btn;
    UsersServices serviceUsers = new UsersServices();
    ArrayList<User> ListVet = new ArrayList<>();
    ArrayList<User> listDress = new ArrayList<>();
    ArrayList<User> listUsers = new ArrayList<>();

    public AffichageProfessionnel() {
        super();
        listUsers.addAll(serviceUsers.getList2());
        Container find = new Container(new BoxLayout(BoxLayout.X_AXIS));
        this.hi.setTitle("Liste Des Professionels");
        cmb = new ComboBox<>();
        ArrayList<String> Roles = new ArrayList<>();
        Roles.add("Veterinaires");
        Roles.add("Dresseurs");
        for (String Role : Roles) {
            cmb.addItem(Role);
        }
        btn = new Button("Recherche");
        find.add(cmb);
        find.add(btn);
        this.hi.add(find);
        lb = new SpanLabel("aaaaaaahmeeeedaaaaaa");
//        lb.setText("" + serviceUsers.getList2().get(0).getEmail());

        System.out.println("" + serviceUsers.getList2().get(0).getGouvernorat());
        // lb.setText("" + serviceTask.getList2().get(0).getId());
//        hi.add(lb);

        btn.addActionListener(
        (evt) -> {
            if (cmb.getSelectedIndex() == 0) {
                listUsers.addAll(ListVet);
                FindUsergui af = new FindUsergui();
                af.getHi().show();

            } else if (cmb.getSelectedIndex() == 1) {
//                listUsers.addAll(listDress);
//                System.out.println("Dress");
//                this.hi = new Form();
//                aff(listDress);
//                System.out.println("dress");
            } else {
//                listUsers.addAll(serviceUsers.getList2());
//                this.hi.refreshTheme();
//                aff(listUsers);

            }

        }
        );
        for (User t : serviceUsers.getList2()) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {

            }
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Image i = (URLImage.createToStorage(enc, t.getGouvernorat(), "http://localhost/pi_dev-master/web/uploads/images/" + t.getImage() + "", URLImage.RESIZE_SCALE));

            ImageViewer img2 = new ImageViewer(i.fill(120, 130));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label lname = new Label(t.getUsername());
            Label lemail = new Label(t.getEmail());
            Label lgouvern = new Label(t.getGouvernorat());
            Label ladresse = new Label("roles" + t.getRole());
            Label lnu = new Label("" + t.getNum_tel());

            C2.add(lname);
            C2.add(lemail);
            C2.add(lgouvern);
            C2.add(ladresse);
            C2.add(lnu);
            C1.add(img2);
            C1.add(C2);
            hi.add(C1);
            C1.setLeadComponent(lname);
            lname.addPointerPressedListener((evt) -> {
                idUserstatic = t.getId();

                AfficherUserGui aj = new AfficherUserGui();
                aj.getHi().show();

            });
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//            lname.addPointerPressedListener((evt) -> {
//                idUserstatic = t.getId();
//                AfficherUserGui aj = new AfficherUserGui();
//                aj.getHi().show();
//            });
        }

    }

}
