/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.user;

import com.esprit.entities.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author Ahmed
 */
public class CategorieServices {

    ArrayList<Categorie> listCategorie = new ArrayList<>();

    public ArrayList<Categorie> getListCategorie1(String json) {
        System.out.println("................." + json);
        ArrayList<Categorie> listucategories = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Categorie e = new Categorie();
                int id = Integer.parseInt(obj.get("id").toString());
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                listucategories.add(e);
            }
        } catch (IOException ex) {
        }
        System.out.println(listucategories);
        return listucategories;

    }

    public ArrayList<Categorie> getListCategorie2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/pi_dev/web/app_dev.php/categories");
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategorieServices ser = new CategorieServices();
                listCategorie = ser.getListCategorie1(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;

    }

    /**
     * **
     */
    public void ajoutTask(Categorie ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8080/pi_dev/web/app_dev.php/categories" + ta.getNom() + "/";
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
    }

    public ArrayList<Categorie> getListTask(String json) {

        ArrayList<Categorie> listEtudiants = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Categorie e = new Categorie();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }
    ArrayList<Categorie> listTasks = new ArrayList<>();

    public ArrayList<Categorie> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/categories");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategorieServices ser = new CategorieServices();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}

/**/
