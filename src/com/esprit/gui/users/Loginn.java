/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import Utilities.ToolsUtilities;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.StringUtil;
import com.esprit.entities.User;
import com.esprit.gui.home.Homegui;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.IOException;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class Loginn extends Bar {

    public static Login loginMethode;
    public Button loginWFace;
    public Button loginWG;

    public Loginn() {

        super();
        UsersServices Us = new UsersServices();

        TextField login = new TextField();
        TextField password = new TextField();
        password.setConstraint(TextField.PASSWORD);
        Button Bconnexion = new Button("connexion");
        Button BIncsciption = new Button("inscription");
        Bconnexion.addActionListener(x -> {

            ConnectionRequest req = new ConnectionRequest();
            req.setUrl(ToolsUtilities.UrlAhmedMakni + "loginMobile/" + login.getText() + "");
            req.setHttpMethod("GET");
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) req.getResponseData();
                    String s = new String(data);
                    User user = RecupererUser(s);
                    String pwd = StringUtil.replaceAll(user.getPasword(), "$2y", "$2a");
                    if (BCrypt.checkpw(password.getText(), pwd) == true) {
                        System.out.println("SUCCESS " + password.getText());
                        User.setUserConncter(user);
                        User.setIdOfConnectedUser(user.getId());

                        Homegui b = new Homegui();
                        b.getHi().show();
                    } else {
                        System.out.println("FAILED");
                        Dialog.show("Erreur", "Merci de vérifier vos paramétres de connexion", "Ok", null);
                    }
                }
            });
            NetworkManager.getInstance().addToQueue(req);

        });

        BIncsciption.addActionListener((evt) -> {
            inscription i = new inscription();
            i.getHi().show();
        });
        int deviceWidht = Display.getInstance().getDisplayWidth();
        int deviceheight = Display.getInstance().getDisplayHeight();
        //Social actionbuttons
        try {
            loginWFace = new Button(EncodedImage.create("/facebookLoginButton.png").scaled(deviceWidht, deviceheight / 8));
        } catch (IOException ex) {
        }
        //loginWFace.setUIID("LoginButton");
        loginWFace.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                //create your own app identity on facebook follow the guide here:
                //facebook-login.html
                String clientId = "160701677931552";
                String redirectURI = "https://www.codenameone.com/";
                String clientSecret = "dfe9f991aaf7267fa0c70a9f79295004";
                //String clientSecret = "2532a457d1ca5c67196df97f9089799d";

                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                loginMethode = fb;

                fb.setCallback(new LoginListener(LoginListener.FACEBOOK));
                if (!fb.isUserLoggedIn()) {
                    fb.doLogin();
                } else {

                    Us.showFacebookUser(fb.getAccessToken().getToken());
                }
            }
        });
        try {
            loginWG = new Button(EncodedImage.create("/googleLogin.png").scaled(deviceWidht, deviceheight / 8));
        } catch (IOException ex) {
        }
        //loginWG.setUIID("LoginButton");
        loginWG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //create your own google project follow the guide here:
                //http://www.codenameone.com/google-login.html
                //String clientId = "839004709667-n9el6dup3gono67vhi5nd0dm89vplrka.apps.googleusercontent.com";
                String clientId = "981240080975-6b5mclv8n9uh23hsppmnb2stga61uq9d.apps.googleusercontent.com";
                String redirectURI = "https://www.codenameone.com/oauth2callback";
                String clientSecret = "P9inMyx-UdmQo9bhEyO8B1Tu";

                Login gc = GoogleConnect.getInstance();
                gc.setClientId(clientId);
                gc.setRedirectURI(redirectURI);
                gc.setClientSecret(clientSecret);
                loginMethode = gc;
                gc.setCallback(new LoginListener(LoginListener.GOOGLE));
                if (!gc.isUserLoggedIn()) {
                    gc.doLogin();
                } else {
                    // Us.showGoogleUser(gc.getAccessToken().getToken());
                }
            }
        });
        Container Butt = new Container(BoxLayout.y());
        this.hi.add(login);
        this.hi.add(password);
        this.hi.add(Bconnexion);
        this.hi.add(BIncsciption);
        Butt.add(loginWFace);
        Butt.add(loginWG);
        this.hi.add(Butt);

    }

    public User authentification(String json) {
        User user = new User();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> Mapprofil = (Map<String, Object>) etudiants.get("User");
            System.out.println(Mapprofil);
            if (Mapprofil != null) {

                user.setId(Integer.parseInt(Mapprofil.get("id").toString()));
                user.setPasword(Mapprofil.get("password").toString());
                user.setUsername(Mapprofil.get("username").toString());
                System.out.println(Mapprofil.get("username").toString());

            }
        } catch (IOException ex) {
        }
        return user;

    }

    public User RecupererUser(String json) {
        User user = new User();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users;
            users = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println("111111" + users);
            if (users.isEmpty()) {
                return null;
            }
            int userId = Integer.valueOf(users.get("id").toString().substring(0, users.get("id").toString().indexOf('.')));
            user.setId(userId);
            user.setPasword(users.get("password").toString());
            user.setEmail(users.get("email").toString());
//            user.setAdresse(users.get("adresse").toString());
            user.setImage(users.get("image").toString());
            user.setRole(users.get("roles").toString());
            user.setUsername(users.get("username").toString());

            return user;
        } catch (IOException ex) {
        }

        return user;

    }

}
