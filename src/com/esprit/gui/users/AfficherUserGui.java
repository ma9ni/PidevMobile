/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.entities.Rating;
import com.esprit.entities.User;
import static com.esprit.gui.users.AffichageProfessionnel.idUserstatic;
import com.esprit.services.rating.RatingService;
import com.esprit.services.user.UsersServices;
import com.esprit.zanimo.Bar;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import com.codename1.googlemaps.MapContainer;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

/**
 *
 * @author makni
 */
public class AfficherUserGui extends Bar {

    private Label nomlab;
    private Label maillab;
    private Label numlab;
    private Label addrelab;
    private Label gouvlab;
    private Label note;
    private Label commentaire;
    private Label Username;
    private TextField giveNote;
    private TextField giveComme;
    private EncodedImage enc;
    private Button addnote;
    private float noteuser = 0;
    private float somme = 0;
    Container C3;
    UsersServices serviceUsers = new UsersServices();
    ArrayList<User> listUsers = new ArrayList<>();
    ArrayList<Rating> listRating = new ArrayList<>();
    User u;

    private static final String HTML_API_KEY = "AIzaSyDcwHdDoI65jU6iHlOGv54Efo67fE_AWw0";

    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
        derive(convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        Slider starRank = new Slider() {
            @Override
            public void refreshTheme(boolean merge) {
                // special case when changing the theme while the dialog is showing
                initStarRankStyle(getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(getSliderFullUnselectedStyle(), fullStar);
            }
        };

        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public AfficherUserGui() {
        super();
        Slider rate = createStarRankSlider();
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        listUsers = serviceUsers.getList2Profes();
        for (User uu : listUsers) {
            if (uu.getId() == idUserstatic) {
                u = uu;
            }
        }
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        Image i = (URLImage.createToStorage(enc, u.getGouvernorat(), u.getImage(), URLImage.RESIZE_SCALE));

        ImageViewer img2 = new ImageViewer(i);

        nomlab = new Label();
        maillab = new Label();
        numlab = new Label();
        addrelab = new Label();
        gouvlab = new Label();
        note = new Label();

        RatingService rs = new RatingService();
        listRating = rs.getList2(u.getId());
//        System.out.println("note" + rs.getList2().get(0).getNote());
        if (!listRating.isEmpty()) {
            for (Rating r : listRating) {
                Username = new Label();
                System.out.println("USername :" + r.getIdUser().getUsername());
                Username.setText(r.getIdUser().getUsername() + " :");
                commentaire = new Label();
                commentaire.setText(r.getCommentaire());
                Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                C3.add(Username);
                C3.add(commentaire);
                C4.add(C3);
                System.out.println("1111111");
                System.out.println("2222222222222");
                System.out.println("noteeee" + r.getNote());
                somme = somme + r.getNote();
                System.out.println("somme1:" + somme);
            }
            System.out.println("size" + listRating.size());
            noteuser = somme / listRating.size();
            rate.setProgress((int) noteuser);
            System.out.println("noteuser" + noteuser);
        } else {
            noteuser = 0;
        }
        giveNote = new TextField("", "note");
        giveComme = new TextField("", "Commentaire");
        addnote = new Button("noter");
//        rate.setProgress();
        nomlab.setText(
        "Nom : " + u.getUsername());
        nomlab.setText(
        "email : " + u.getEmail());
        numlab.setText(
        "N° Tel : " + u.getNum_tel());
        addrelab.setText(
        "Adresse : " + u.getAdresse());
        gouvlab.setText(
        "Gouvernorat : " + u.getGouvernorat());
        note.setText(
        "note : " + noteuser);
        C2.add(img2);

        C2.add(nomlab);

        C2.add(numlab);

        C2.add(addrelab);

        C2.add(gouvlab);
        C2.add(C4);
        C2.add(note);
        C2.add(giveNote);
        C2.add(giveComme);
        C2.add(addnote);

        rate.addActionListener((ActionListener) (ActionEvent evt1) -> {
            giveNote.setText("Note:" + rate.getProgress());
            System.out.println("rating :" + rate.getProgress());
        });

        C2.add(rate);
        addnote.addActionListener((evt) -> {
            User userCo = new User(9);
            Rating r = new Rating(rate.getProgress(), giveComme.getText(), null, u, userCo);
            System.out.println("rating:::" + r);
            rs.ajoutRating(r);
            AfficherUserGui aj = new AfficherUserGui();
            aj.getHi().show();
        });
        this.hi.getToolbar()
        .addCommandToOverflowMenu("retour", null, (evt) -> {
            AffichageProfessionnel aj = new AffichageProfessionnel();
            aj.getHi().show();
        }
        );
        this.hi.setTitle("afficher " + u.getUsername());
        hi.add(C2);
        Style s = new Style();
        s.setFgColor(0x8A2BE2);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
        MapContainer cn = new MapContainer();

        cn.zoom(getCoords(u.getGouvernorat()), 18);

        cn.setCameraPosition(getCoords(u.getGouvernorat())); // since the image is iin the jar this is unlikely
        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords(u.getGouvernorat()), "Hi marker", "Optional long description", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
            }
        });
        C2.add(cn);
        System.out.println(
        "ussserAhmeeeeeeeeeeeeed" + u);
    }

}
