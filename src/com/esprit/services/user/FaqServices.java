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
import com.esprit.entities.Question;
import com.esprit.entities.Reponse;
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
public class FaqServices {

    ArrayList<Question> listTasks = new ArrayList<>();
    Question accessoireResultat = new Question();

    public ArrayList<Question> getListTask(String json) {
        ArrayList<Question> listEtudiants = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Question e = new Question();
                Reponse saReponse = new Reponse();
                float id = Float.parseFloat(obj.get("id").toString());

                float etattemp = Float.parseFloat(obj.get("etat").toString());
                e.setId((int) id);
                e.setQuestion(obj.get("question").toString());
                e.setEtat((int) etattemp);
                Map<String, Object> tempsrep = (Map<String, Object>) obj.get("idReponse");
                float idreponse = Float.parseFloat(tempsrep.get("id").toString());
                saReponse.setId((int) idreponse);
                saReponse.setReponse(tempsrep.get("reponse").toString());
                e.setIdReponse(saReponse);
                listEtudiants.add(e);
            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }

    public ArrayList<Question> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_questions");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FaqServices ser = new FaqServices();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void posterQuestion(Question question) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi_dev-master/web/app_dev.php/mobile_proposer_question"
                + "?question=" + question.getQuestion();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    //propsition
    public ArrayList<Question> getListhaha(String json) {
        ArrayList<Question> listEtudiants = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Question e = new Question();
                float id = Float.parseFloat(obj.get("id").toString());

                float etattemp = Float.parseFloat(obj.get("etat").toString());
                e.setId((int) id);
                e.setQuestion(obj.get("question").toString());
                e.setEtat((int) etattemp);
                listEtudiants.add(e);
            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }

    public ArrayList<Question> gestQuestionAttentes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/mobile_questions_admin");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FaqServices ser = new FaqServices();
                listTasks = ser.getListhaha(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    //repondre
    public void repondreQuestion(Question question, Reponse reponse) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi_dev-master/web/app_dev.php/mobile_reponse_admin"
                + "?question=" + question.getQuestion()
                + "&reponse=" + reponse.getReponse();
        System.out.println(Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
