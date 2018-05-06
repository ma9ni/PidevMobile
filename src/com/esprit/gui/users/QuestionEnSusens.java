/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.components.SpanButton;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.entities.Question;
import com.esprit.services.user.FaqServices;
import com.esprit.zanimo.Bar;

/**
 *
 * @author Ahmed
 */
public class QuestionEnSusens extends Bar {

    public FaqServices faqService;

    public QuestionEnSusens() {
        super();
        faqService = new FaqServices();
        this.hi.setTitle("Les question en attentes");
        for (Question question : faqService.gestQuestionAttentes()) {
            SpanButton laquestionBtn = new SpanButton("- " + question.getQuestion());
            laquestionBtn.getAllStyles().setBorder(Border.createEmpty());
            laquestionBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            laquestionBtn.addActionListener((evt) -> {
                RepondreQuestion passage = new RepondreQuestion(question);
                passage.getHi().show();
            });
            this.hi.add(laquestionBtn);
        }

    }

}
