/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.animal;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.esprit.entities.animal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salah
 */
public class animalsusersevices {

    public void ajoutTask(animal an) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://41.226.11.243:10004/tasks/" + an.getNom() + "/" + an.getDescription();
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

    public ArrayList<animal> getListTask(String json) {

        ArrayList<animal> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                animal e = new animal();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                String image = obj.get("image").toString();
                e.setImage(image);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNom(obj.get("nom").toString());
                e.setNom(obj.get("nomproprietaire").toString());
                //  e.setNom(obj.get("nomproprietaire").toString());
                e.setDescription(obj.get("description").toString());
                e.setRace(obj.get("race").toString());
                e.setSexe(obj.get("sexe").toString());

                //e.setDatedenaissance(obj.get("datedenaissance.timezone.transitions.0.time").toString());
                // String datestr = ((Map) ((Map) ((Map) obj.get("datedenaissance")).get("timezone")).get("transitions")).get("time").toString();
                // System.out.println("OBJ " + obj);
                // System.out.println("OBJ " + ((LinkedHashMap) obj.get("datedenaissance")).get("timezone"));
                // e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    ArrayList<animal> listTasks = new ArrayList<>();

    public ArrayList<animal> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/Symfony/pi_dev/web/app_dev.php/allanimal/1");
        con.addResponseListener((NetworkEvent evt) -> {
            animalservices ser = new animalservices();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void modifier_animal(animal an) {

        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://127.0.0.1/Symfony/pi_dev/web/app_dev.php/animal/edit?" + "id=" + an.getId() + "&description=" + an.getDescription() + "&nom=" + an.getNom();
        con.setUrl(Url);

        System.out.println("tt");

        System.out.println("111111111" + Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("222222222" + str);
            System.out.println("333333333" + Url);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
