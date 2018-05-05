/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Question;
import com.esprit.entities.Reponse;
import com.esprit.gui.home.Homegui;
import com.esprit.services.user.FaqServices;

/**
 *
 * @author Ahmed
 */
public class RepondreQuestion {

    private Form hi;

    public RepondreQuestion(Question question) {
        this.hi = new Form();
        //pour la toolbar

        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar(new Command("Retour") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GererMesAnnonces temp = new GererMesAnnonces();
                temp.getHi().show();
            }
        });

        //pour la forme
        SpanLabel uneQuestion = new SpanLabel(question.getQuestion());

        Label reponseLabel = new Label("Reponse : ");
        TextArea reponseArea = new TextArea(3, 20);
        Container reponseContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reponseContainer.add(reponseLabel);
        reponseContainer.add(reponseArea);
        Button repondreBtn = new Button("Confirmer");
        repondreBtn.addActionListener((evt) -> {
            FaqServices faqService = new FaqServices();
            Reponse reponse = new Reponse();
            reponse.setReponse(reponseArea.getText());
            faqService.repondreQuestion(question, reponse);
            Homegui fqs = new Homegui();
            fqs.getHi().show();
        });
        this.hi.add(uneQuestion);
        this.hi.add(reponseContainer);
        this.hi.add(repondreBtn);
    }

    public Form getHi() {
        return hi;
    }

}
