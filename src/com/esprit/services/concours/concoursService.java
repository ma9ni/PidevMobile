package com.esprit.services.concours;

import Utilities.ToolsUtilities;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.esprit.entities.StatsConcours;
import com.esprit.entities.concours;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author angham
 */
public class concoursService {

    public void ajoutTask(concours c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://41.226.11.243:10004/tasks/" + c.getTitre() + "/" + c.getRace();
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

    public ArrayList<concours> getListTask(String json) {

        ArrayList<concours> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                concours c = new concours();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                // System.out.println(id);
                c.setId((int) id);

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                c.setTitre(obj.get("titre").toString());
                c.setType(obj.get("type").toString());
                c.setRace(obj.get("race").toString());
                float capacite = Float.parseFloat(obj.get("capacite").toString());
                c.setCapacite((int) capacite);
                float nbpar = Float.parseFloat(obj.get("nbparticipant").toString());
                c.setNbparticipant((int) nbpar);

                Map<String, Object> datedebutjson = (Map<String, Object>) obj.get("dateDebut");
                float datefloat = Float.parseFloat(datedebutjson.get("timestamp").toString());
                Date l = new Date((long) datefloat * 1000);

                Map<String, Object> datefinjson = (Map<String, Object>) obj.get("dateFin");
                float datefinfloat = Float.parseFloat(datefinjson.get("timestamp").toString());
                Date dfdf = new Date((long) datefinfloat * 1000);
                c.setDateFin(dfdf);
                // e.setDatedenaissance(obj.get("datedenaissance.timezone.transitions.0.time").toString());
                // String datestr = ((Map) ((Map) ((Map) obj.get("datedenaissance")).get("timezone")).get("transitions")).get("time").toString();
                // System.out.println("OBJ " + obj);
                // System.out.println("OBJ " + ((LinkedHashMap) obj.get("datedenaissance")).get("timezone"));
                // e.setNom(obj.get("name").toString());
                //System.out.println(e);
                c.setDateDebut(l);
                listEtudiants.add(c);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    ArrayList<concours> listTasks = new ArrayList<>();

    public ArrayList<concours> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "Concours/anghamm");
        con.addResponseListener((NetworkEvent evt) -> {
            concoursService ser = new concoursService();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    //l'ajout d'un concours
    public void ajoutConcours(concours c) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateee = format.format(c.getDateDebut());
        String datiiiii = format.format(c.getDateFin());
        String Url = ToolsUtilities.UrlAhmedMakni + "Concours/mob_ajout?"
        + "&titre=" + c.getTitre()
        + "&capa=" + c.getCapacite()
        + "&race=" + c.getRace()
        + "&type=" + c.getType()
        + "&dd=" + dateee
        + "&df=" + datiiiii;
        System.out.println("__ Url__ :" + Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        System.out.println("le concours est ajouter a notre BD");
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    //modification d'un concours
    public concours getList1010(int idConcours) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "Concours/anghamm2?id=" + idConcours);
        con.addResponseListener((NetworkEvent evt) -> {
            concoursService ser = new concoursService();
            listTasks = ser.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        concours res = listTasks.get(0);
        return res;
    }

    public void modifierConcours(concours c) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateee = format.format(c.getDateDebut());
        String datiiiii = format.format(c.getDateFin());
        String Url = ToolsUtilities.UrlAhmedMakni + "Concours/mob_mod?"
        + "&id=" + c.getId()
        + "&titre=" + c.getTitre()
        + "&capa=" + c.getCapacite()
        + "&race=" + c.getRace()
        + "&type=" + c.getType()
        + "&dd=" + dateee
        + "&df=" + datiiiii;
        System.out.println("__ Url__ :" + Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        System.out.println("le concours est modifier a notre BD");
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    //la suppression d'un concours
    public void supprimerConcours(int idConcour) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = ToolsUtilities.UrlAhmedMakni + "Concours/mob_supp/" + idConcour;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    /**
     * ****************** STATS ********************************************
     */
    public ArrayList<StatsConcours> getListStats(String json) {

        ArrayList<StatsConcours> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                StatsConcours c = new StatsConcours();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("nombre").toString());
                // System.out.println(id);
                c.setNbre((double) id);

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                c.setType(obj.get("type").toString());

                // String datestr = ((Map) ((Map) ((Map) obj.get("datedenaissance")).get("timezone")).get("transitions")).get("time").toString();
                // System.out.println("OBJ " + obj);
                // System.out.println("OBJ " + ((LinkedHashMap) obj.get("datedenaissance")).get("timezone"));
                // e.setNom(obj.get("name").toString());
                //System.out.println(e);
                listEtudiants.add(c);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    ArrayList<StatsConcours> listStats = new ArrayList<>();

    public ArrayList<StatsConcours> getStats() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "Concours/concours_stats");
        con.addResponseListener((NetworkEvent evt) -> {
            concoursService ser = new concoursService();
            listStats = ser.getListStats(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStats;
    }

}
