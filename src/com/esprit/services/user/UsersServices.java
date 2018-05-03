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
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                String nomevnt = obj.get("gouvernorat").toString();
                int numtel = Integer.parseInt(obj.get("numTel").toString());
                String img = obj.get("image").toString();
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

}
