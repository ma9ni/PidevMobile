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
import com.esprit.entities.Accessoire;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed
 */
public class AccessoireServices {

    public ArrayList<Accessoire> getListTask(String json) {

        ArrayList<Accessoire> listEtudiants = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            for (Map<String, Object> obj : list) {
                Accessoire e = new Accessoire();
                Map<String, Object> dtea = (Map<String, Object>) obj.get("datePublication");
                float id = Float.parseFloat(obj.get("id").toString());
                float dateA = Float.parseFloat(dtea.get("timestamp").toString());
                Date dateAjout = new Date((long) dateA * 1000);
                e.setDatePublication(dateAjout);
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setCategorie(obj.get("categorie").toString());
                e.setPhoto("http://localhost/pi_dev-master/web/uploads/images/" + obj.get("photo").toString());
                e.setDescription(obj.get("description").toString());
                e.setQteStock(obj.get("qutite").toString());
                e.setPrix(Float.parseFloat(obj.get("prix").toString()));
                e.setIdMembre(1);
                listEtudiants.add(e);
            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }
    ArrayList<Accessoire> listTasks = new ArrayList<>();
    Accessoire accessoireResultat = new Accessoire();

    public ArrayList<Accessoire> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_accessoires");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AccessoireServices ser = new AccessoireServices();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void ajouterAccessoire(Accessoire accessoire) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi_dev-master/web/app_dev.php/mobile_ajouter_accessoire"
                + "?nom=" + accessoire.getNom()
                + "&description=" + accessoire.getDescription()
                + "&prix=" + accessoire.getPrix()
                + "&categorie=" + accessoire.getCategorie()
                + "&validite=" + accessoire.getValiditePublication()
                + "&photo=" + accessoire.getPhoto()
                + "&qte=" + accessoire.getQteStock()
                + "&qte=" + accessoire.getQteStock();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public Accessoire getAccessoire(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_get_accessoire/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AccessoireServices ser = new AccessoireServices();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        accessoireResultat = listTasks.get(0);
        return accessoireResultat;
    }

}
