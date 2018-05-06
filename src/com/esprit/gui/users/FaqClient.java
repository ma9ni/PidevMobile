/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.entities.Question;
import com.esprit.services.user.FaqServices;
import com.esprit.zanimo.Bar;
import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class FaqClient extends Bar {

    FaqServices faqservice;

    public FaqClient() {
        super();
        int deviceWidth = Display.getInstance().getDisplayWidth();
        this.hi.setTitle("Foire aux questions");
        faqservice = new FaqServices();
        for (Question question : faqservice.getList2()) {
            Container heyla = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            heyla.setWidth(deviceWidth);
            SpanButton laquestionBtn = new SpanButton("- " + question.getQuestion());
            laquestionBtn.getAllStyles().setBorder(Border.createEmpty());
            laquestionBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            laquestionBtn.setWidth(deviceWidth);
            laquestionBtn.addActionListener((evt) -> {
                UneQuestion passage = new UneQuestion(question);
                passage.getHi().show();
            });
            heyla.add(laquestionBtn);
            this.hi.add(heyla);
        }
        //pour la description de l'accesoire
        Label votreQuestionLabel = new Label("Votre Question : ");
        TextArea votreQuestionArea = new TextArea(2, 20);
        Container questionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button endahBtn = new Button("Poster");
        endahBtn.setWidth(deviceWidth);
        endahBtn.addActionListener((evt) -> {
            if (votreQuestionArea.getText().indexOf("?") < 0) {
                this.hi.refreshTheme();
                votreQuestionLabel.getAllStyles().setFgColor(0xff0000);
                Label ligne1Eror = new Label();
                Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
                ligne1Eror.setText("Il nous faut une question");
                ligne1Eror.getAllStyles().setFgColor(0xff0000);
                ligne1Eror.getAllStyles().setFont(smallBoldSystemFont);
                questionContainer.add(ligne1Eror);

            } else {
                Question laQuestionPropose = new Question();
                laQuestionPropose.setQuestion(votreQuestionArea.getText());
                faqservice.posterQuestion(laQuestionPropose);
                if (Dialog.show("Felicitation", "Merci, votre question est proposer a notre comité ", "ok", null)) {
                    FaqClient passe = new FaqClient();
                    passe.getHi().show();
                }
            }
        });
        questionContainer.add(votreQuestionLabel);
        questionContainer.add(votreQuestionArea);
        questionContainer.add(endahBtn);
        Button proposerQuestion = new Button("Proposer une question ");
        proposerQuestion.setWidth(deviceWidth);
        proposerQuestion.addActionListener((evt) -> {
            this.hi.removeAll();
            this.hi.refreshTheme();
            this.hi.add(questionContainer);
        });
        this.hi.add(proposerQuestion);
    }

}
