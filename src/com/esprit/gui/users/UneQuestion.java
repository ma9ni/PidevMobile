/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.esprit.entities.Question;

/**
 *
 * @author Ahmed
 */
public class UneQuestion {

    private Form hi;

    public UneQuestion(Question question) {
        this.hi = new Form();
        Toolbar tb = hi.getToolbar();
        tb.addCommandToLeftBar(new Command("retour") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FaqClient temp = new FaqClient();
                temp.getHi().show();
            }
        });
        this.hi.setTitle("Question");
        SpanLabel questionLabel = new SpanLabel(question.getQuestion());
        SpanLabel reponseLabel = new SpanLabel(question.getIdReponse().getReponse());
        this.hi.addAll(questionLabel, reponseLabel);

    }

    public Form getHi() {
        return hi;
    }

}
