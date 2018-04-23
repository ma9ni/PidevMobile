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
                System.out.println(id);
                e.setIdAdoption((int) id);
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
    
}
