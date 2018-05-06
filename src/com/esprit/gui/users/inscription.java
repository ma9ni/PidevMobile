/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayoutStyle;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.StringUtil;
import com.esprit.entities.User;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.io.InputStream;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class inscription extends Bar {

    String pphoto;

    public inscription() {
        super();

        Validator val = new Validator();

        Container c1 = new Container(BoxLayout.y());
        Container c2 = new Container(BoxLayout.x());
        Container c3 = new Container(BoxLayout.x());
        Container c4 = new Container(BoxLayout.y());

        Label pseaudo = new Label("Pseudo");
        TextField tpseaudo = new TextField();
        c1.add(pseaudo);
        c1.add(tpseaudo);

        Label Email = new Label("Email");
        TextField tEMail = new TextField();
        c1.add(Email);
        c1.add(tEMail);

        Label MotdePasse = new Label("Mot de passe");
        TextField tmotdepasse = new TextField();
        tmotdepasse.setConstraint(TextField.PASSWORD);
        c1.add(MotdePasse);
        c1.add(tmotdepasse);

        Label confirmermotdepasse = new Label("Confirmer Mot de passe");
        TextField tconf = new TextField();
        tconf.setConstraint(TextField.PASSWORD);

        c1.add(confirmermotdepasse);
        c1.add(tconf);

        Label Role = new Label("Role");
        ComboBox CRole = new ComboBox<>();
        String[] lesTYpes = {"Utilisateur", "Veterinaire", "Dresseur", "Petiteur"};
        for (int i = 0; i < 4; i++) {
            CRole.addItem(lesTYpes[i]);

        }
        c2.add(Role);
        c2.add(CRole);

        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
//        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        if (evt == null) {
                            return;
                        }
                        String filename = (String) evt.getSource();

                        if (Dialog.show("Send file?", filename, "OK", "Cancel")) {
                            MultipartRequest req = new MultipartRequest();
                            String endpoint = "http://localhost/pi/pi_dev/web/app_dev.php/uploadPhotoMobileProfil";
                            System.out.println("endpoint  : " + endpoint);
                            req.setUrl(endpoint);
                            req.addArgument("message", "test");
                            InputStream is = null;
                            try {
                                is = FileSystemStorage.getInstance().openInputStream(filename);
                                req.addData("file", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
                                req.setFilename("file", filename);//any unique name you want
                                req.addResponseListener(new ActionListener<NetworkEvent>() {
                                    @Override
                                    public void actionPerformed(NetworkEvent evt) {
                                        byte[] data = (byte[]) req.getResponseData();
                                        String s = new String(data);
                                        if (s.equals("ok")) {
// entité = nom de la photo
                                            System.out.println("SUCCESS");
                                            String newfilename = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
                                            pphoto = filename.substring(filename.lastIndexOf('/') + 1);
                                            photoField.setText("http://localhost/pi/pi_dev/web/uploads/images/" + filename.substring(filename.lastIndexOf('/') + 1));
                                            // photoField.setText(filename);
                                            pphoto = (newfilename);
                                        }
                                    }
                                });
                                NetworkManager.getInstance().addToQueue(req);
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }

                }, Display.GALLERY_IMAGE);

            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }
        });
        c3.add(photoLabel);
        c3.add(photoField);
        c3.add(selectPhoto);

        val.addConstraint(Email, new LengthConstraint(6));
        Button BInscription = new Button("Inscription");
        UsersServices US = new UsersServices();
        Label Erreur = new Label("");
        int a = 1;

        // buton de l'inscription
        BInscription.addActionListener((evt) -> {

            System.out.println("taat" + tEMail.getText());
            ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/loginMobile/" + tEMail.getText() + "");
            req.setHttpMethod("GET");
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) req.getResponseData();
                    String s = new String(data);
                    Loginn login = new Loginn();
                    User user = login.RecupererUser(s);
                    if (user != null) {
                        System.out.println("aaaa");
                        Erreur.setText("Email existe déja ");
                        c4.add(Erreur);
                        hi.add(c4);
                        hi.show();
                    } else {

                        System.out.println("sucess");
                        Dialog.show("sccess", "bienvenu vous etes inscrit", "Ok", null);
                        String hasch = BCrypt.hashpw(tmotdepasse.getText(), BCrypt.gensalt());
                        String pwd = StringUtil.replaceAll(hasch, "$2a", "$2y");

                        User UserInscri = new User(tpseaudo.getText(), tEMail.getText(), pwd, photoField.getText(), "", CRole.getSelectedItem().toString());
                        System.out.println("hedhaya user2 " + UserInscri);

                        UserInscri.setRole(CRole.getSelectedItem().toString());
                        System.out.println("hedhaya user3 " + UserInscri);
                        System.out.println(CRole.getSelectedItem().toString());
                        System.out.println(UserInscri.getRole());
                        UsersServices us = new UsersServices();
                        us.inscription(UserInscri);
                    }
                }
            });
            NetworkManager.getInstance().addToQueue(req);

        });

        hi.add(c1);
        hi.add(c2);
        hi.add(c3);
        hi.add(BInscription);
        // hi.add(Erreur);
        hi.show();

    }

}
