/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.concours;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.concours;
import com.esprit.services.concours.concoursService;

/**
 *
 * @author angham
 */
public class EditerConcours {

    private Form hi;
    final Label capaLabel = new Label("capacité");
    int nb = 0;

    public EditerConcours(int idConcours) {
        this.hi = new Form("Modifier");
        // pour le btn retours
        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar(new Command("retour") {
            public void actionPerformed(ActionEvent evt) {
                AffichageConcours temp = new AffichageConcours();
                temp.getHi().show();
            }
        });
        concoursService concoutrsservice = new concoursService();
        affichagedunConcours(concoutrsservice.getList1010(idConcours), idConcours);

    }

    public void affichagedunConcours(concours unConcours, int idd) {
        //pour le titre du concours
        Label titreLabel = new Label("Titre");
        TextField titreArea = new TextField("", "Titre", 12, TextArea.ANY);
        titreArea.setText(unConcours.getTitre());
        Container titreContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        titreContainer.add(titreLabel);
        titreContainer.add(titreArea);

        //pour le type du concours
        Label typeLabel = new Label("Type");
        ComboBox typeCombo = new ComboBox();
        String[] lestypes = {"chien", "chat", "lapin", "poisson"};
        for (int i = 0; i < 4; i++) {// on a 4 car nous avons 4 type de concours
            typeCombo.addItem(lestypes[i]);
        }
        typeCombo.setSelectedItem(unConcours.getType());
        Container TypeContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        TypeContainer.add(typeLabel);
        TypeContainer.add(typeCombo);

        //pour la race des animaux du concours
        Label raceLabel = new Label("Race");
        TextField raceArea = new TextField("", "Race", 12, TextArea.ANY);
        raceArea.setText(unConcours.getRace());
        Container raceContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        raceContainer.add(raceLabel);
        raceContainer.add(raceArea);

        //pour la capacité du concours
        Slider capacArea = new Slider();
        capacArea.setEditable(true);
        capacArea.setEnabled(true);
        capacArea.setMaxValue(200);
        capaLabel.setText("capacité : " + unConcours.getCapacite());

        capacArea.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                capaLabel.setText("capacité : " + index);
                nb = index;
            }
        });
        Container capacContainer = new Container(new GridLayout(2, 1));
        capacContainer.add(capaLabel);
        capacContainer.add(capacArea);

        //pour la date de debut  du concours
        Label ddLabel = new Label("date debut");
        Picker ddPicker = new Picker();
        ddPicker.setDate(unConcours.getDateDebut());
        Label ddEror = new Label("*");
        ddEror.getAllStyles().setFgColor(0xff0000);
        ddEror.setVisible(false);
        Container ddContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ddContainer.add(ddLabel);
        ddContainer.add(ddPicker);
        ddContainer.add(ddEror);

        //pour la date de fin  du concours
        Label dfLabel = new Label("date Fin");
        Picker dfPicker = new Picker();
        dfPicker.setDate(unConcours.getDateFin());
        Label dfEror = new Label("*");
        dfEror.getAllStyles().setFgColor(0xff0000);
        dfEror.setVisible(false);
        Container dfContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        dfContainer.add(dfLabel);
        dfContainer.add(dfPicker);
        dfContainer.add(dfEror);

        //pour que les date soient affiché l'une a cote de l'autre
        Container datesContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datesContainer.add(ddContainer);
        datesContainer.add(dfContainer);

        //boutton valider
        Button btnValider = new Button("Valider");
        btnValider.addActionListener((evt) -> {

            concours temp = new concours();
            temp.setId(idd);
            temp.setTitre(titreArea.getText());
            temp.setType(typeCombo.getSelectedItem().toString());
            temp.setRace(raceArea.getText());
            temp.setDateDebut(ddPicker.getDate());
            temp.setDateFin(dfPicker.getDate());
            concoursService tempService = new concoursService();
            tempService.modifierConcours(temp);
            //al la fin de notre action de l'ajout du concours un dialog va s'afficher et nous allons rediger l'utilisateur a la liste des concours
            Dialog.show("felicitation", "le concours est modifier", "OK", null);
            AffichageConcours inte = new AffichageConcours();
            inte.getHi().show();

        }
        );
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

        btnContainer.add(btnValider);

        this.hi.setLayout(
                new BoxLayout(BoxLayout.Y_AXIS));

        this.hi.add(titreContainer);

        this.hi.add(TypeContainer);

        this.hi.add(raceContainer);

        this.hi.add(capacContainer);

        this.hi.add(datesContainer);

        this.hi.add(btnContainer);

    }

    public Form getHi() {
        return hi;
    }

}
