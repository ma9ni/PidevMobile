/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.rating;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Rating;
import com.esprit.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author makni
 */
public class RatingService {

    public void ajoutRating(Rating ra) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi_dev-master/web/app_dev.php/newRating?" + "&note=" + ra.getNote() + "&commentaire=" + ra.getCommentaire() + "&idUser=" + ra.getIdUser().getId() + "&idMembreCO=" + ra.getIdMembreCO().getId();
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

    public ArrayList<Rating> getListRating(String json) {

        ArrayList<Rating> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Rating e = new Rating();

                float id = Float.parseFloat(obj.get("id").toString());
                float note = Float.parseFloat(obj.get("note").toString());
                String commentaire = (obj.get("commentaire").toString());
                e.setId((int) id);
                e.setNote((int) note);
                e.setCommentaire(commentaire);

                Map<String, Object> UserRating = (Map<String, Object>) obj.get("idUser");
                User u = new User();
                float idra = Float.parseFloat(UserRating.get("id").toString());
                String image = UserRating.get("image").toString();
                String username = UserRating.get("username").toString();
                String email = UserRating.get("email").toString();
                u.setId((int) idra);
                u.setImage(image);
                u.setUsername(username);
                u.setEmail(email);
                e.setIdUser(u);

                Map<String, Object> UserRatingCo = (Map<String, Object>) obj.get("idMembre");
                User uCo = new User();
                float idraCo = Float.parseFloat(UserRatingCo.get("id").toString());
                String imageCo = UserRatingCo.get("image").toString();
                String usernameCo = UserRatingCo.get("username").toString();
                String emailCo = UserRatingCo.get("email").toString();
                u.setId((int) idraCo);
                u.setImage(imageCo);
                u.setUsername(usernameCo);
                u.setEmail(emailCo);
                e.setIdMembreCO(uCo);

                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    ArrayList<Rating> listRatings = new ArrayList<>();

    public ArrayList<Rating> getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi_dev-master/web/app_dev.php/ratingmobileUser/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RatingService ser = new RatingService();
                listRatings = ser.getListRating(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRatings;
    }
}
