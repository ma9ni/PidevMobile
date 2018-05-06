package com.esprit.services.animal;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.esprit.entities.User;
import com.esprit.entities.animal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salah
 */
public class animalservices {

    public void ajouterGateau(animal an) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = tempss.format(an.getDatedenaissance());
        String Url = "http://127.0.0.1/Symfony/pi_dev/web/app_dev.php/animal/new"
                + "?nom=" + an.getNom()
                + "&description=" + an.getDescription()
                + "&photo=" + an.getImage()
                + "&sexe=" + an.getSexe()
                + "&race=" + an.getRace()
                + "&date=" + dateString;
        System.out.println("sfsdfsdfsdfd_____" + Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
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
                //  e.setNom(obj.get("nomproprietaire").toString());
                e.setDescription(obj.get("description").toString());
                e.setRace(obj.get("race").toString());
                e.setSexe(obj.get("sexe").toString());
                Map<String, Object> listmembre = (Map<String, Object>) obj.get("id_membre");
                User user = new User();
                user.setEmail((String) listmembre.get("email"));
                e.setId_membre(user);

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
        con.setUrl("http://127.0.0.1/Symfony/pi_dev/web/app_dev.php/animal/");
        con.addResponseListener((NetworkEvent evt) -> {
            animalservices ser = new animalservices();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}
