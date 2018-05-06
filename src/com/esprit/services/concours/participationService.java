/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.concours;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.esprit.entities.animal;
import com.esprit.entities.concours;
import com.esprit.entities.participer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author angham
 */
public class participationService {

    public void ajoutparticip(participer p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/Concours/mobile/sub?concour=" + p.getIdconcour() + "&animal=" + p.getIdanimal();
        con.setUrl(Url);
        System.out.println(Url);

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

    public ArrayList<animal> getListTask(String json) {

        ArrayList<animal> listanimal = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> animal = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(animal);

            List<Map<String, Object>> list = (List<Map<String, Object>>) animal.get("root");

            for (Map<String, Object> obj : list) {
                animal c = new animal();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String race = obj.get("race").toString();
// System.out.println(id);
                c.setId((int) id);
                c.setRace(race);
                c.setNom(nom);
                System.out.println(c);
                listanimal.add(c);

            }

        } catch (IOException ex) {
        }
        System.out.println(listanimal);
        return listanimal;

    }
    ArrayList<animal> listTasks = new ArrayList<>();

    public ArrayList<animal> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/Concours/mobile/allanimal");
        con.addResponseListener((NetworkEvent evt) -> {
            participationService ser = new participationService();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}
