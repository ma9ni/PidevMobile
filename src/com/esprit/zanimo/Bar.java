/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.zanimo;

import com.codename1.components.ImageViewer;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.gui.Adoption.AffichageAdoptionGui;
import com.esprit.gui.animal.affichergui;
import com.esprit.gui.home.Homegui;

import com.esprit.gui.users.AffichageProfessionnel;
import com.esprit.gui.users.AjouterFs;
import com.esprit.gui.users.Loginn;
import com.esprit.gui.users.Profil;

/**
 *
 * @author makni
 */
public class Bar {

    protected Form hi;
    protected Resources theme;

    public Bar() {

        theme = UIManager.initFirstTheme("/theme");
        hi = new Form("Best Pets");
        Toolbar tb = hi.getToolbar();
        ImageViewer img = new ImageViewer(theme.getImage("1.jpg"));
        // hi.add(img);
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AjouterFs aj = new AjouterFs();
                aj.getHi().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Proffessionel", FontImage.MATERIAL_MONEY_OFF, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //hi.show();
                AffichageProfessionnel aj = new AffichageProfessionnel();
                aj.getHi().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hi.show();
            }
        });
        tb.addMaterialCommandToSideMenu("Annonce", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hi.show();
            }
        });
        tb.addMaterialCommandToSideMenu("Concours", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hi.show();
            }
        });
                tb.addMaterialCommandToSideMenu("Adoption", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      AffichageAdoptionGui aj = new AffichageAdoptionGui();
                      aj.getHi().show();

            }
          });  

        
                tb.addMaterialCommandToSideMenu("animal", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                affichergui aj = new affichergui();
                aj.getHi().show();
            }
        });
                
                if(User.getIdOfConnectedUser()==0){
                                    tb.addMaterialCommandToSideMenu("Login & Sign In", FontImage.MATERIAL_HOME, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent evt) {
                               Loginn aj = new Loginn();
                               aj.getHi().show();
                           }
                       }); 
                }else{
                     tb.addMaterialCommandToSideMenu("déconnexion", FontImage.MATERIAL_HOME, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent evt) {
                               User.setIdOfConnectedUser(0);
                               Homegui aj = new Homegui();
                               aj.getHi().show();
                           }
                       }); 
                }
     
                if(User.getIdOfConnectedUser()!=0){
                                    tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_HOME, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent evt) {
                               Profil aj = new Profil();
                               aj.getHi().show();
                           }
                       }); 
                }
    }

    public Form getHi() {
        return hi;
    }

}
