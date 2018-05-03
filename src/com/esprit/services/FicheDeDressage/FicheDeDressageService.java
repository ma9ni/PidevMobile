/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.FicheDeDressage;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.FicheDeDressage;
import com.esprit.entities.animal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author makni
 */
public class FicheDeDressageService {

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    Date d;

    public void ajoutfichedeDressage(FicheDeDressage ta) {

//        ConnectionRequest con = new ConnectionRequest();
//
//        String datdebu = formater.format(ta.getProchainRDV());
//
//        String Url = "http://localhost/pi_dev-master/web/app_dev.php/newfiche?" + "&observation=" + ta.getObservation() + "&medicament=" + ta.getMedicament() + "&dateCreation=" + ta.getDateCreation() + "&prochainRDV=" + datdebu + "&etat=" + ta.getEtat() + "&id_animal=" + ta.getId_animal().getId() + "&id_membre=" + ta.getId_membre().getId();
//        con.setUrl(Url);
//
//        System.out.println("tt");
//
//        System.out.println("111111111" + Url);
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//            System.out.println("222222222" + str);
//            System.out.println("333333333" + Url);
////            if (str.trim().equalsIgnoreCase("OK")) {
////                f2.setTitle(tlogin.getText());
////             f2.show();
////            }
////            else{
////            Dialog.show("error", "login ou pwd invalid", "ok", null);
////            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<FicheDeDressage> getListFicheDeDressage(String json) {

        ArrayList<FicheDeDressage> ListFicheDeDressages = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> root = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("Json :" + root);
            List<Map<String, Object>> list = (List<Map<String, Object>>) root.get("root");
            for (Map<String, Object> obj : list) {
                FicheDeDressage e = new FicheDeDressage();
                float id = Float.parseFloat(obj.get("id").toString());
                float Displine = Float.parseFloat(obj.get("displine").toString());
                float Interception = Float.parseFloat(obj.get("interception").toString());
                float Obeissance = Float.parseFloat(obj.get("obeissance").toString());
                float NoteTotal = Float.parseFloat(obj.get("noteTotale").toString());
                float accompagnement = Float.parseFloat(obj.get("accompagnement").toString());
                System.out.println("id fiche de soin " + id);
                e.setId((int) id);
                e.setSpecialite(obj.get("specialite").toString());
                e.setDispline(Displine);
                e.setObeissance(Obeissance);
                e.setInterception(Interception);
                e.setNoteTotal(NoteTotal);
                e.setAccompagnement(accompagnement);
                Map<String, Object> animal = (Map<String, Object>) obj.get("idAnimal");
                Map<String, Object> prch = (Map<String, Object>) obj.get("dateDebut");
                Map<String, Object> datecreation = (Map<String, Object>) obj.get("dateFin");
                System.out.println("tttttt" + prch.get("timestamp").toString());
                float dateStr = Float.parseFloat(prch.get("timestamp").toString());
                float datecreatio = Float.parseFloat(datecreation.get("timestamp").toString());
                Date l = new Date((long) dateStr * 1000);
                Date l2Date = new Date((long) datecreatio * 1000);
                e.setDateDebut(l);
                e.setDateFin(l2Date);
                animal u = new animal(animal.get("nom").toString(), animal.get("nomproprietaire").toString(), animal.get("image").toString());

                String img = animal.get("image").toString();

                u.setNom(animal.get("nom").toString());
                u.setImage(img);
                u.setNomproprietaire(animal.get("nomproprietaire").toString());
                e.setId_animal(u);
                ListFicheDeDressages.add(e);

            }

        } catch (IOException ex) {
            ex.toString();
        }

        return ListFicheDeDressages;

    }
    ArrayList<FicheDeDressage> listdressage = new ArrayList<>();

    public ArrayList<FicheDeDressage> getList2FicheDeDressages() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/ficheDeDressage/12");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FicheDeDressageService ser = new FicheDeDressageService();
                listdressage = ser.getListFicheDeDressage(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listdressage;
    }

    public void modifierfichedeSoin(FicheDeDressage ta) {

        ConnectionRequest con = new ConnectionRequest();

        String datdebu = formater.format(ta.getDateDebut());
        String datfin = formater.format(ta.getDateFin());

        String Url = "http://localhost/pi_dev-master/web/app_dev.php/modifierfichededress?" + "id=" + ta.getId() + "&specialite=" + ta.getSpecialite() + "&accompagnement=" + ta.getAccompagnement() + "&obeissance=" + ta.getObeissance() + "&displine=" + ta.getDispline() + "&interception=" + ta.getInterception() + "&noteTotale=" + ta.getNoteTotal() + "&dateDebut=" + datdebu + "&dateFin=" + datfin;
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
