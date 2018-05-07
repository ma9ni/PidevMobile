/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.animal;

import Utilities.ToolsUtilities;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.esprit.entities.sosDisparition;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salah
 */
public class sosservices {

    public void ajoutTask(sosDisparition sos) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = tempss.format(sos.getDate());
        String Url = ToolsUtilities.UrlAhmedMakni + "sosdisparition/new"
        + "?nomproprietaire=" + sos.getNomproprietaire()
        + "&description=" + sos.getDescription()
        + "&date=" + dateString
        + "&num_tel=" + sos.getNum_tel()
        + "&photo=" + sos.getImage()
        + "&race=" + sos.getRace()
        + "&lieu=" + sos.getLieu()
        + "&adresse=" + sos.getAdresse()
        + "&date=" + dateString;
        System.out.println("sfsdfsdfsdfd_____" + Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<sosDisparition> getListTask(String json) {

        ArrayList<sosDisparition> listEtudiants = new ArrayList<>();

        try {
            System.out.println("json:" + json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("1111111" + etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                sosDisparition s = new sosDisparition();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                s.setId((int) id);
                String image = obj.get("image").toString();
                s.setImage(image);
                s.setNomproprietaire(obj.get("nomproprietaire").toString());
                s.setDescription(obj.get("description").toString());
                s.setAdresse(obj.get("adresse").toString());
                s.setRace(obj.get("race").toString());
                s.setLieu(obj.get("lieu").toString());
                float num_tel = Float.parseFloat(obj.get("numTel").toString());
                s.setNum_tel((int) num_tel);
                listEtudiants.add(s);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;
    }

    ArrayList<sosDisparition> listTasks = new ArrayList<>();

    public ArrayList<sosDisparition> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "sosdisparition/");
        con.addResponseListener((NetworkEvent evt) -> {
            sosservices ser = new sosservices();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}
