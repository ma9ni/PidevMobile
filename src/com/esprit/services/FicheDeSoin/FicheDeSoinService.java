/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.FicheDeSoin;

import Utilities.ToolsUtilities;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.FicheDeSoin;
import com.esprit.entities.User;
import com.esprit.entities.animal;
import com.esprit.services.animal.animalservices;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author makni
 */
public class FicheDeSoinService {

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    Date d;
    private String message;

    public void ajoutfichedeSoin(FicheDeSoin ta) {

        ConnectionRequest con = new ConnectionRequest();

        String datdebu = formater.format(ta.getProchainRDV());

        String Url = ToolsUtilities.UrlAhmedMakni + "newfiche?" + "&observation=" + ta.getObservation() + "&medicament=" + ta.getMedicament() + "&dateCreation=" + ta.getDateCreation() + "&prochainRDV=" + datdebu + "&etat=" + ta.getEtat() + "&id_animal=" + ta.getId_animal().getId() + "&id_membre=" + ta.getId_membre().getId();
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

    public ArrayList<FicheDeSoin> getListTask(String json) {

        ArrayList<FicheDeSoin> listEtudiants = new ArrayList<>();

        try {

            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("Json :" + etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                FicheDeSoin e = new FicheDeSoin();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("id fiche de soin " + id);
                e.setId((int) id);
                e.setObservation(obj.get("observation").toString());
                e.setMedicament(obj.get("medicament").toString());

                Map<String, Object> animal = (Map<String, Object>) obj.get("idAnimal");
                Map<String, Object> prch = (Map<String, Object>) obj.get("prochainRDV");
                Map<String, Object> datecreation = (Map<String, Object>) obj.get("dateCreation");
                System.out.println("tttttt" + prch.get("timestamp").toString());
                float dateStr = Float.parseFloat(prch.get("timestamp").toString());
                float datecreatio = Float.parseFloat(datecreation.get("timestamp").toString());
                // Long l = Long.parseLong(dateStr);
                Date l = new Date((long) dateStr * 1000);
                Date l2Date = new Date((long) datecreatio * 1000);
                System.out.println(l);
                // SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                // String datdebu = formater.format(l);
                // System.out.println(datdebu);
                e.setProchainRDV(l);
                e.setDateCreation(l2Date);

                //   System.out.println("aaaaaaaa" + birthDate);
                //then
                //  user.setBirthdate(birthDate);
                animal u = new animal(animal.get("nom").toString(), animal.get("nomproprietaire").toString(), animal.get("image").toString());

                String nom = animal.get("nom").toString();
                String img = animal.get("image").toString();
                String nomproprietaire = animal.get("nomproprietaire").toString();
                System.out.println("nom animal :" + nom);
                System.out.println("nom animal :" + nomproprietaire);
                u.setNom(animal.get("nom").toString());
                u.setImage(img);
                u.setNomproprietaire(animal.get("nomproprietaire").toString());
                e.setId_animal(u);

                System.out.println("animal :" + u);
                System.out.println("ah        : " + e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
            ex.toString();
        }
        System.out.println("liste Etudiant " + listEtudiants);
        return listEtudiants;

    }

    ArrayList<FicheDeSoin> listfichedeSoin = new ArrayList<>();

    public ArrayList<FicheDeSoin> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "ficheDeSoin/" + User.getUserConncter().getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FicheDeSoinService ser = new FicheDeSoinService();
                listfichedeSoin = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listfichedeSoin;
    }

    public ArrayList<animal> getListanimal(String json) {

        ArrayList<animal> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
//                e.setId((int) id);
//                e.setNom(obj.get("nom").toString());
//                e.setDescription(obj.get("description").toString());
                animal e = new animal((int) id, obj.get("nom").toString(), obj.get("description").toString());
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }

    ArrayList<animal> listanimal = new ArrayList<>();

    public ArrayList<animal> getListanimal2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "animallle");
        con.addResponseListener((NetworkEvent evt) -> {
            animalservices ser = new animalservices();
            listanimal = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listanimal;
    }

    public void modifierfichedeSoin(FicheDeSoin ta) {

        ConnectionRequest con = new ConnectionRequest();

        String datdebu = formater.format(ta.getProchainRDV());

        String Url = ToolsUtilities.UrlAhmedMakni + "modifierfiche?" + "id=" + ta.getId() + "&observation=" + ta.getObservation() + "&medicament=" + ta.getMedicament() + "&prochainRDV=" + datdebu;
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

    public void supprimerficheDeDressage(FicheDeSoin ta) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/dellficheDesoin/" + ta.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    message = new String(con.getResponseData(), "utf-8");
                    System.out.println("message" + message);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.toString());
                }
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
