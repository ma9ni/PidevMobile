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
import com.esprit.entities.User;
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

    //pour recuperer le user
    User bendirman = new User();
    ArrayList<User> listannonceur = new ArrayList<>();

    public ArrayList<User> getListAnnonceur(String json) {
        ArrayList<User> listAnnonceur = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> useres = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) useres.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();
                e.setUsername(obj.get("username").toString());
//                e.setNum_tel(Integer.parseInt(obj.get("num_tel").toString()));
                e.setEmail(obj.get("email").toString());
//                e.setAdresse(obj.get("gouvernorat").toString());
                listAnnonceur.add(e);
            }
        } catch (IOException ex) {
        }
        return listAnnonceur;
    }

    public User getAnnonceur(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_get_annonceur/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AccessoireServices ser = new AccessoireServices();
                listannonceur = ser.getListAnnonceur(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        bendirman = listannonceur.get(0);
        return bendirman;
    }

    //pour la gestion des accessoires
    public ArrayList<Accessoire> mesAccessoires(int idmembreconnecte) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_mes_annonces/" + idmembreconnecte);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AccessoireServices ser = new AccessoireServices();
                listTasks = ser.listeMesAccessoires(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<Accessoire> listeMesAccessoires(String json) {

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

    public ArrayList<Accessoire> supprimerAccessoire(int idannonce) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_effacer_annonces/" + idannonce);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AccessoireServices ser = new AccessoireServices();
                listTasks = ser.listeMesAccessoires(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;

    }

    //pour la modification
    public void modifierAccessoire(Accessoire accessoire) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi_dev-master/web/app_dev.php/mobile_modifier_annonces/"
                + accessoire.getId()
                + "?nom=" + accessoire.getNom()
                + "&description=" + accessoire.getDescription()
                + "&prix=" + accessoire.getPrix()
                + "&categorie=" + accessoire.getCategorie()
                + "&validite=" + accessoire.getValiditePublication()
                + "&qte=" + accessoire.getQteStock()
                + "&qte=" + accessoire.getQteStock();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
