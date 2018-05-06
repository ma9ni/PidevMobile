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
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Adoption;
import com.esprit.entities.User;
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
 
                //pour l'animal
                Map<String, Object> animal = (Map<String, Object>) obj.get("idAnimal");
                animal u = new animal(animal.get("nom").toString(),
                        animal.get("nomproprietaire").toString(), animal.get("image").toString());
                String img = animal.get("image").toString();
                
                String race = animal.get("race").toString();
                u.setRace(race);
                u.setImage(img);

                
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idAdoption").toString());
                String description =(String) obj.get("description");
                System.out.println(id);
                e.setIdAdoption((int) id);
                e.setDescription(description);
                e.setType((String) obj.get("type"));
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                               
                e.setIdAnimal(u);

                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }

            public ArrayList<Adoption> getVosListAdoption(String json) {

        ArrayList<Adoption> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                
                               
                Map<String, Object> membre = (Map<String, Object>) obj.get("idMembre");
                float idm = Float.parseFloat(membre.get("id").toString());
                int idmembre=(int)idm;
                System.out.println("idmembre= "+idmembre);
                System.out.println("idd user connecter= "+ User.getUserConncter().getId());

                if (idmembre==User.getUserConncter().getId()) {
                    
                Adoption e = new Adoption();
 
                //pour l'animal
                Map<String, Object> animal = (Map<String, Object>) obj.get("idAnimal");
                animal u = new animal(animal.get("nom").toString(),
                        animal.get("nomproprietaire").toString(), animal.get("image").toString());
                String img = animal.get("image").toString();
                
                String race = animal.get("race").toString();
                u.setRace(race);
                u.setImage(img);

                
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idAdoption").toString());
                String description =(String) obj.get("description");
                System.out.println(id);
                e.setIdAdoption((int) id);
                e.setDescription(description);
                e.setType((String) obj.get("type"));
                e.setLieu((String) obj.get("lieu"));
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                               
                e.setIdAnimal(u);

                System.out.println(e);
                listEtudiants.add(e);
                    
                }


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
    
    public ArrayList<Adoption> getList2VosAdoption(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/allAdoption");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AdoptionService ser = new AdoptionService();
                listAdoptions = ser.getVosListAdoption(new String(con.getResponseData()));
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
                +adoption.getIdAnimal().getId()+"&type="+type+"&idUser="+adoption.getIdMembre().getId());
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        System.out.println("lajout de l'annonce d'adoption  est effectuee");
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    ArrayList<animal> listanimal = new ArrayList<>();

    public ArrayList<animal> getListanimal2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/listeAnimalUserMobile/"+User.getUserConncter().getId());
        con.addResponseListener((NetworkEvent evt) -> {
            animalservices ser = new animalservices();
            listanimal = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listanimal;
    }


//public Adoption getAdoption(int ) {
//    ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/allAdoption");  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                AdoptionService ser = new AdoptionService();
//                listAdoptions = ser.getListAdoption(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listAdoptions;
//}

    
    public void ajoutReclamation(int idAdoption , String Sujet, String message){
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/AjoutReclamation?idAdoption="+idAdoption
        +"&Sujet="+Sujet+"&message="+message+"&idUser="+User.getUserConncter().getId());
        con.addResponseListener((e) -> {
            //String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                System.out.println("lajout de reclamatin est effectuer");

   
    
    }
    
    public void SupprimerAdoption(int id){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/SuppAdoptionnn/"+id);
     con.addResponseListener((e) -> {
            //String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                System.out.println("la supprision de l'adoption est effectuer");

        
    }
    public void modifierAdoption(Adoption adoption){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/pi_dev/web/app_dev.php/ModifierAdoption?id="+adoption.getIdAdoption()
        +"&description="+adoption.getDescription()+"&lieu="+adoption.getLieu());
     con.addResponseListener((e) -> {
            //String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                System.out.println("la supprision de l'adoption est effectuer");

        
    }
    
    public void envoyerMessage(){
 Message m = new Message("Body of message");
Display.getInstance().sendMessage(new String[] {"someone@gmail.com"}, "Subject of message", m);
    }
        
        
        
    
}
