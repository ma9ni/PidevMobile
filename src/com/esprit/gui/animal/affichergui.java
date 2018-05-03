/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.animal;


import com.codename1.ui.Label;
import com.esprit.services.animal.animalservices;
import com.esprit.zanimo.Bar;

/**
 *
 * @author salah
 */
public class affichergui extends Bar{
 Label lb;
  Label lb2;

    public affichergui() {
        super();
        lb = new Label("");
        lb2= new Label("");
        animalservices serviceTask=new animalservices();
        lb.setText(serviceTask.getList2().get(0).getNom());
        lb2.setText(serviceTask.getList2().get(0).getDescription());
        System.out.println(""+serviceTask.getList2().get(0).getNom());
        this.hi.add(lb); 
        this.hi.add(lb2); 
    }
    
}
