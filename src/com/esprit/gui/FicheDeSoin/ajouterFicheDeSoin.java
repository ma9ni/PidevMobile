/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.FicheDeSoin;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.FicheDeSoin;
import com.esprit.entities.User;
import com.esprit.entities.animal;
import com.esprit.services.FicheDeSoin.FicheDeSoinService;

import com.esprit.zanimo.Bar;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author makni
 */
public class ajouterFicheDeSoin extends Bar {

    private SpanLabel lb;
    private Label observation;
    private Label Medicament;
    private Label ProchainRDV;
    private Label Animal;
    private TextField Textobservation;
    private TextField TextMedicament;
    private TextField TextProchainRDV;
    private Picker p;
    private ComboBox cmb;
    FicheDeSoin fds;
    private Button ajout;
    FicheDeSoinService fsDeSoinService = new FicheDeSoinService();

    public ajouterFicheDeSoin() {
        super();

        cmb = new ComboBox<>();
        this.hi.getToolbar().addCommandToOverflowMenu("retour", null, (e) -> {

            afficherFicheDeSoingui aj = new afficherFicheDeSoingui();
            aj.getHi().show();
        });
        Container hii = new Container(new TableLayout(2, 2));
        this.hi.setTitle("Ajouter Fiche De Soin");
        lb = new SpanLabel();
//        lb = new SpanLabel("aaaaaaahmeeeedaaaaaa");

        ArrayList<animal> anim = new ArrayList<>();
        anim.addAll(fsDeSoinService.getListanimal2());

        for (animal object : anim) {
            cmb.addItem(object.getNom());
        }
        observation = new Label("observation");
        Medicament = new Label("Medicament");
        ProchainRDV = new Label("Prochain RDV");
        Animal = new Label("Animal");

        Textobservation = new TextField("", "observation");
        TextMedicament = new TextField("", "Medicament");
        p = new Picker();
        p.setType(Display.PICKER_TYPE_DATE);
        hii.add(observation);
        hii.add(Textobservation);
        hii.add(Medicament);
        hii.add(TextMedicament);
        hii.add(ProchainRDV);
        hii.add(p);
        hii.add(Animal);
        hii.add(cmb);
//        TextModeLayout tl = new TextModeLayout(3, 2);
//        TextComponent title = new TextComponent().label("Title");
//        TextComponent price = new TextComponent().label("Price");
//        TextComponent location = new TextComponent().label("Location");
//        TextComponent description = new TextComponent().label("Description").multiline(true);
//        hii.add(tl.createConstraint().horizontalSpan(2), title);
//        hii.add(tl.createConstraint().widthPercentage(30), price);
//        hii.add(tl.createConstraint().widthPercentage(70), location);
//        hii.add(tl.createConstraint().horizontalSpan(2), description);

        p.addActionListener((evt) -> {
            Date d = p.getDate();
            System.out.println("" + d);
//            System.out.println(title.getField().getText());
        });

//        hi.setEditOnShow(title.getField());
        // System.out.println(title.);
        //  observation = (Label)title;
        // System.out.println("" + title.text(title.toString()));
        hi.add(hii);
//        lb = new SpanLabel("");

        //  lb.setText(serviceanimal.getListanimal2().toString());
        cmb.addActionListener((evt) -> {

            animal a = anim.get(cmb.getSelectedIndex());
            System.out.println(a);
            System.out.println(cmb.getSelectedItem());
        });
        ajout = new Button("Ajouter");

//        hi.add(lb);
        hi.add(ajout);
        ajout.addActionListener((evt) -> {
            Date d = p.getDate();
            animal a = anim.get(cmb.getSelectedIndex());
            System.out.println("animaaaaaaaaaaaaaaaaaaal" + cmb.getSelectedIndex());
            User u = new User(8);
            fds = new FicheDeSoin(u, Textobservation.getText(), TextMedicament.getText(), d, a, null, 1);
            System.out.println("ficheeeeeeeeeeeee" + fds);
            fsDeSoinService.ajoutfichedeSoin(fds);
        });

    }

}
