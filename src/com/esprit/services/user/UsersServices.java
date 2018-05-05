/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.user;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.entities.User;
import com.esprit.gui.home.Homegui;
import com.esprit.gui.users.Loginn;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author makni
 */
public class UsersServices {
 int a;
    public ArrayList<User> getListUser(String json) {

        ArrayList<User> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();

                // System.out.println(obj.get("id"));
                String id = obj.get("gouvernorat").toString();
                //   String name
                System.out.println(id);
                e.setGouvernorat(id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
//                e.setEtat(obj.get("state").toString());
//                e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }

    ArrayList<User> listUsers = new ArrayList<>();

    public ArrayList<User> getListUsersProfesionel(String json) {

        ArrayList<User> listEvenement = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(evenements);

            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();

                // System.out.println(obj.get("id"));
                 float id = Float.parseFloat(obj.get("id").toString());
                String nomevnt = obj.get("gouvernorat").toString();
                /*  float nbp = Float.parseFloat(obj.get("nombredeplaces").toString());
                float nbpr = Float.parseFloat(obj.get("nombredesplacesrestante").toString());
                float tarif = Float.parseFloat(obj.get("tarifevenement").toString());
                String description = obj.get("descriptionevenement").toString();
                String lieu = obj.get("lieu").toString();
                String date = obj.get("date").toString();*/
                String email = obj.get("email").toString();
                String img = obj.get("image").toString();

                /* Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println(users);

                List<Map<String, Object>> listUsers = (List<Map<String, Object>>) users.get("iduser");
                 for (Map<String, Object> user : listUsers)
                 {
                     Users u = new Users();

                     float idU = Float.parseFloat(user.get("id").toString());
                     e.setIdUser((int)idU);
                 }*/
 /*
               Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");
                System.out.println(user.get("id"));
                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));


                System.out.println(id);
                System.out.println(nomevnt);
                System.out.println(nbp);
                System.out.println(nbpr);


                Map<String, Object> time  = (Map<String, Object>) user.get("datedenaissance");
                Map<String, Object> time2  = (Map<String, Object>) time.get("timezone");
              //  Map<String, Object> time3  = (Map<String, Object>) time2.get("transitions");
              //  System.out.println(time3.get("time"));



                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));


                System.out.println(id);
                 */
                  e.setId((int) id);
                e.setGouvernorat(nomevnt);
                e.setImage(img);
                /*  e.setNombredeplaces((int)nbp);
                e.setNombredeplacerestante((int)nbpr);
                e.setDescription(description);
                e.setTarifevenement(tarif);
                e.setLieu(lieu);
                e.setDate(date);
                e.setDatefin(datefin);
*/                e.setEmail(email);

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                // e.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());

              //  float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
               // String nomevnt = obj.get("gouvernorat").toString();
                int numtel = Integer.parseInt(obj.get("numTel").toString());
                //String img = obj.get("image").toString();
                e.setAdresse(obj.get("adresse").toString());
                e.setUsername(obj.get("username").toString());
                e.setEmail(obj.get("email").toString());
                ArrayList<String> UserRole = (ArrayList<String>) obj.get("roles");
                e.setNum_tel(numtel);
                e.setRole(UserRole.get(0));
                e.setGouvernorat(nomevnt);
                e.setImage(img);
                System.out.println(e);
                listEvenement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvenement);
        return listEvenement;

    }

    public ArrayList<User> getList2Profes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/usersmobile");
        // con.setHttpMethod("get");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UsersServices ser = new UsersServices();
                listUsers = ser.getListUsersProfesionel(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }
   
// public boolean isEmail(String s) {
//       return s.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+");
//    }
 public int emailExiste(String Email){
      a =0;
     ConnectionRequest con = new ConnectionRequest();
 con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/loginMobile/" + Email + "");
        con.setHttpMethod("GET");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Loginn u = new Loginn();
 byte[] data = (byte[]) con.getResponseData();
                String s = new String(data);
                User user = u.RecupererUser(s);
        if (user!=null) {
           a=1;
         
     }
            }
        });
      
      //  System.out.println("aaaaasd"+listEvenements);
                NetworkManager.getInstance().addToQueueAndWait(con);
                return a;

 }
 public int PseudoExiste(String Pseudo)
 {
     int a =0;
     ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/PseaudoExiste/"+Pseudo);
       if (con.getResponseData()!=null) {
           a=1;         
     }
                NetworkManager.getInstance().addToQueueAndWait(con);
                return a;

 }
 public void inscription(User user ){
     System.out.println("dfdsfdsf"+user.getEmail());
             ConnectionRequest req = new ConnectionRequest();
             req.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/Inscription?username="
                +user.getUsername()+"&password="+user.getPasword()+"&email="
                +user.getEmail()+"&role="+user.getRole());
       
       //// System.out.println("1");
       //req.setHttpMethod("GET");
       System.out.println("1111111111"+req.getUrl());
         req.addResponseListener((e) -> {
            System.out.println("1");
            String str = new String(req.getResponseData());
             System.out.println("22222222"+req.getUrl());
             System.out.println("333333333"+str);
            User.setUserConncter(user);
         User.setIdOfConnectedUser(3);
                    Homegui b = new Homegui();
                    b.getHi().show(); 
        });
         //System.out.println("3");
         NetworkManager.getInstance().addToQueueAndWait(req);


     
 }
         public User RecupererUser(String json) {
                    User user = new User();

            try {
            JSONParser j = new JSONParser();
            Map<String, Object> users;
            users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            

            System.out.println("111111"+users);
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
             public void showFacebookUser(String token) {
        ConnectionRequest req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl("https://graph.facebook.com/v2.3/me");
        req.addArgumentNoEncoding("access_token", token);
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        NetworkManager.getInstance().addToQueueAndWait(req);
        byte[] data = req.getResponseData();
        JSONParser parser = new JSONParser();
        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String name = (String) map.get("name");
        d.dispose();

        User socialUser = new User();
        try {
            socialUser.setUsername(name);
        } catch (Exception ex) {

        }

        try {
            List<String> tokenzed = StringUtil.tokenize(name, " ");
            System.out.println("tokenzed = " + tokenzed);
            //socialUser.setNom(tokenzed.get(0));
           // socialUser.setPrenom(tokenzed.get(1));
        } catch (Exception ex) {

        }

        try {
           // socialUser.setSocialid((String) map.get("id"));
        } catch (Exception ex) {

        }

        try {
            socialUser.setImage("https://graph.facebook.com/v2.3/me/picture?access_token=" + token);
        } catch (Exception ex) {

        }

        // check existance before socialSignIn
        UsersServices um = new UsersServices();
        User user = um.RecupererUser(name);

        System.out.println("SOCIAL USER : " + user);
        if (user != null) {
            User.setUserConncter(user);
            User.setIdOfConnectedUser(user.getId());
            Homegui acceuil = new Homegui();
            acceuil.getHi().show();
        } else {
            um.inscription(user); 
        }

        //invoke Social Sign iN
        /*
        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), "https://graph.facebook.com/v2.3/me/picture?access_token=" + token);
        userForm.show();
         */
    }

//    public void showGoogleUser(String token) {
//
//        //Submit this newly created user
//        ConnectionRequest req = new ConnectionRequest();
//        req.addRequestHeader("Authorization", "Bearer " + token);
//        req.setUrl("https://www.googleapis.com/plus/v1/people/me");
//        req.setPost(false);
//        InfiniteProgress ip = new InfiniteProgress();
//        Dialog d = ip.showInifiniteBlocking();
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        d.dispose();
//        byte[] data = req.getResponseData();
//        JSONParser parser = new JSONParser();
//
//        Map map = null;
//        try {
//            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        //String name = (String) map.get("displayName");
//        Map im = (Map) map.get("image");
//        String url = (String) im.get("url");
//        ArrayList email = (ArrayList) map.get("emails");
//        System.out.println("MAP" + map);
//        System.out.println("email = " + email);
//        LinkedHashMap nomprn = (LinkedHashMap) map.get("name");
//        System.out.println("nomprn = " + nomprn);
//
//        User socialUser = new User();
//        try {
//            //String[] compo = name.split(" ");
//            socialUser.setLogin(map.get("displayName").toString());
//        } catch (Exception ex) {
//
//        }
//
//        try {
//            Map mapMail = (Map) email.get(0);
//            System.out.println("mapMail");
//            socialUser.setEmail(mapMail.get("value").toString());
//        } catch (Exception ex) {
//
//        }
//
//        try {
//            socialUser.setNom(nomprn.get("givenName").toString());
//            socialUser.setPrenom(nomprn.get("familyName").toString());
//        } catch (Exception ex) {
//
//        }
//
//        try {
//            socialUser.setSocialid((String) map.get("id"));
//        } catch (Exception ex) {
//
//        }
//
//        try {
//            socialUser.setPhotoProfil(url);
//        } catch (Exception ex) {
//        }
//
//        System.out.println("PRINTED USERRRR" + socialUser);
//
//        // check existance before socialSignIn
//        UserManager um = new UserManager();
//        User user = um.checkExistanceBySocialId(socialUser.getSocialid());
//        System.out.println("SOCIAL USER : " + user);
//        if (user != null) {
//            User.setActifUser(user);
//            User.setIdOfConnectedUser(user.getId());
//            AcceuilForm acceuil = new AcceuilForm(current);
//            acceuil.show();
//        } else {
//            SocialSignInForm socialSign = new SocialSignInForm(current, socialUser);
//            socialSign.show();
//        }/*
//        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), url);
//        userForm.show();
//         */
//    }


}
