/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.Adoption;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Adoption;
import com.esprit.entities.animal;
import com.esprit.gui.users.AjouterFs;
import com.esprit.services.animal.animalservices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class AdoptionService {
        public ArrayList<Adoption> getListAdoption(String json) {

        ArrayList<Adoption> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Adoption e = new Adoption();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idAdoption").toString());
                String description =(String) obj.get("description");
                System.out.println(id);
                e.setIdAdoption((int) id);
                e.setDescription(description);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }

    
        ArrayList<Adoption> listAdoptions = new ArrayList<>();

    public ArrayList<Adoption> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/allAdoption");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AdoptionService ser = new AdoptionService();
                listAdoptions = ser.getListAdoption(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAdoptions;
    }
    
    
    public void AjouterAdoption(Adoption adoption){
        String type="donner" ;
        if (adoption.getType().equals("Donner temporairement votre animal")) {
            type="deleger";
            
        }
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/AjoutAdoption_mobile?description="
                +adoption.getDescription()+"&lieu="+adoption.getLieu()+"&id_animal="
                +adoption.getIdAnimal().getId()+"&type="+type);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        System.out.println("lajout de l'annonce d'adoption  est effectuee");
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    ArrayList<animal> listanimal = new ArrayList<>();

    public ArrayList<animal> getListanimal2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/listeAnimalUserMobile/"+id);
        con.addResponseListener((NetworkEvent evt) -> {
            animalservices ser = new animalservices();
            listanimal = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listanimal;
    }
}
