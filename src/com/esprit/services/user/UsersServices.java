/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.user;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.entities.User;

/**
 *
 * @author makni
 */
public class UsersServices {

    /* public ArrayList<User> getListUser(String json) {

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

    public ArrayList<User> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/veterinaires");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UsersServices ser = new UsersServices();
                listUsers = ser.getListUser(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }*/
 /* public void newEvent(Evenements ev) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(ev.getNomevenement());
        String Url = "http://localhost/pidevmobile/web/app_dev.php/Evenements/new/"+ev.getNomevenement()+"/"+ev.getLieu()+"/"+ev.getDate()+"/"+ev.getDatefin()+"/"+ev.getNombredeplaces()+"/"+ev.getDescription();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
    public ArrayList<User> getListTask(String json) {

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
                // float id = Float.parseFloat(obj.get("id").toString());
                String nomevnt = obj.get("gouvernorat").toString();
                int numtel = Integer.parseInt(obj.get("numTel").toString());
                /*  float nbp = Float.parseFloat(obj.get("nombredeplaces").toString());
                float nbpr = Float.parseFloat(obj.get("nombredesplacesrestante").toString());
                float tarif = Float.parseFloat(obj.get("tarifevenement").toString());
                String description = obj.get("descriptionevenement").toString();
                String lieu = obj.get("lieu").toString();
                String date = obj.get("date").toString();
                String datefin = obj.get("datefin").toString();*/
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
                //  e.setId((int) id);
                e.setNum_tel(numtel);
                e.setGouvernorat(nomevnt);
                e.setImage(img);
                /*  e.setNombredeplaces((int)nbp);
                e.setNombredeplacerestante((int)nbpr);
                e.setDescription(description);
                e.setTarifevenement(tarif);
                e.setLieu(lieu);
                e.setDate(date);
                e.setDatefin(datefin);
                e.setFile(img);*/

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                // e.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEvenement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvenement);
        return listEvenement;

    }
    ArrayList<User> listEvenements = new ArrayList<>();

    public ArrayList<User> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/usersmobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UsersServices ser = new UsersServices();
                listEvenements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }
}
