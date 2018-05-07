/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.zanimo;

import Utilities.ToolsUtilities;
import com.codename1.io.Storage;
import com.codename1.ui.Command;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.gui.Adoption.AffichageAdoptionGui;

import com.esprit.gui.users.AjouterAccessoire;
import com.esprit.gui.users.*;

import com.esprit.gui.FicheDeDressage.afficherFicheDeDressageGUI;
import com.esprit.gui.FicheDeSoin.afficherFicheDeSoingui;
import com.esprit.gui.animal.affichergui;
import com.esprit.gui.concours.AffichageConcours;
import com.esprit.gui.concours.AjouterConcours;
import com.esprit.gui.concours.ParticiperConcours;
import com.esprit.gui.home.Homegui;
import com.esprit.gui.home.Statistiques;

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
    private Image userPicture = null;

    public Bar() {
        theme = UIManager.initFirstTheme("/theme");
        hi = new Form("Best Pets");
//        ImageViewer limage = new ImageViewer();
//        limage.setImage(theme.getImage("1.jpg"));
//        Toolbar tb = hi.getToolbar();
        //  hi.add(limage);
        Storage.getInstance().deleteStorageFile("imgprofile");
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("fbuser.jpg"), true);
        Image src = null;
        try {
            String imgLink = User.getUserConncter().getImage();

            if (User.getIdOfConnectedUser() == 0) {
                Storage.getInstance().deleteStorageFile("imgprofile");
                src = URLImage.createToStorage(placeholder, User.getUserConncter().getImage(), User.getUserConncter().getImage(), URLImage.RESIZE_SCALE);
            } else {
                Storage.getInstance().deleteStorageFile("imgprofile");
                src = URLImage.createToStorage(placeholder, User.getUserConncter().getImage(), ToolsUtilities.UrlAhmedMakniImage + "web/uploads/images/" + User.getUserConncter().getImage(), URLImage.RESIZE_SCALE);
            }
        } catch (NullPointerException ne) {
            src = URLImage.createToStorage(placeholder, "imgprofile", "", URLImage.RESIZE_SCALE);
            System.out.println("Photo null");
        }
        userPicture = src;

        final Command profileCommand = new Command("My Profile", userPicture) {
            public void actionPerformed(ActionEvent evt) {

                // updateLoginPhoto();
//                ProfileForm profileForm = new ProfileForm(sender);
//                profileForm.show();

                /*sender.getContentPane().removeAll();
                sender.addComponent(BorderLayout.CENTER, showMyProfile());
                sender.revalidate();
                 */
                if (User.getIdOfConnectedUser() != 0) {
                    Profil aj = new Profil();
                    aj.getHi().show();
                }
            }
        };
        hi.addCommand(profileCommand);

        if (User.getIdOfConnectedUser() == 0) {

            hi.getToolbar().addCommandToOverflowMenu("Login", null, (evt) -> {
                Loginn aj = new Loginn();
                aj.getHi().show();
            });

        } else {

            final Command addCommand = new Command("Ajouter Concours") {
                public void actionPerformed(ActionEvent evt) {
                    AjouterConcours ajjConcours = new AjouterConcours();
                    ajjConcours.getHi().show();
                }
            };

            hi.addCommand(addCommand);

            final Command afficherConCommand = new Command("Afficher Concours user") {
                public void actionPerformed(ActionEvent evt) {
                    ParticiperConcours ajjConcours = new ParticiperConcours();
                    ajjConcours.getHi().show();
                }
            };

            hi.addCommand(afficherConCommand);
            hi.getToolbar().addCommandToOverflowMenu("déconnexion", null, (evt) -> {
                User.setIdOfConnectedUser(0);
                Homegui aj = new Homegui();
                aj.getHi().show();
            });
        }

        final Command HomeCommand = new Command("Home") {
            public void actionPerformed(ActionEvent evt) {
                AjouterFs aj = new AjouterFs();
                aj.getHi().show();
            }
        };

        hi.addCommand(HomeCommand);

        final Command ProffesionelCommand = new Command("Proffessionel") {
            public void actionPerformed(ActionEvent evt) {
                AffichageProfessionnel aj = new AffichageProfessionnel();
                aj.getHi().show();
            }
        };

        hi.addCommand(ProffesionelCommand);

        final Command ProduitsCommand = new Command("Produits") {
            public void actionPerformed(ActionEvent evt) {
                ListeAccessoires ajouterAccessoires = new ListeAccessoires();
                ajouterAccessoires.getHi().show();
            }
        };

        hi.addCommand(ProduitsCommand);

        final Command AnnonceCommand = new Command("Annonce") {
            public void actionPerformed(ActionEvent evt) {
                AjouterAccessoire ajouterAccessoire = new AjouterAccessoire();
                ajouterAccessoire.getHi().show();
            }
        };

        hi.addCommand(AnnonceCommand);

        final Command AdoptionCommand = new Command("Adption") {
            public void actionPerformed(ActionEvent evt) {
                AjouterAccessoire ajouterAccessoire = new AjouterAccessoire();
                ajouterAccessoire.getHi().show();
            }
        };

        hi.addCommand(AdoptionCommand);

        final Command ConcoursCommand = new Command("Concours") {
            public void actionPerformed(ActionEvent evt) {
                AffichageConcours affichageConcours = new AffichageConcours();
                affichageConcours.getHi().show();
            }
        };

        hi.addCommand(ConcoursCommand);

        if (User.getIdOfConnectedUser() != 0) {
            if (User.getUserConncter().getRole().contains("ROLE_VETE")) {
                System.out.println("Veterinaire");
                final Command FicheDeSoinCommand = new Command("Mes Fiche De Soin") {
                    public void actionPerformed(ActionEvent evt) {
                        afficherFicheDeSoingui aj = new afficherFicheDeSoingui();
                        aj.getHi().show();
                    }
                };

                hi.addCommand(FicheDeSoinCommand);
            } else if (User.getUserConncter().getRole().contains("ROLE_DRESS")) {
                final Command FicheDeDressageCommand = new Command("Mes Fiche De Dressage") {
                    public void actionPerformed(ActionEvent evt) {
                        afficherFicheDeDressageGUI aj = new afficherFicheDeDressageGUI();
                        aj.getHi().show();
                    }
                };
                hi.addCommand(FicheDeDressageCommand);
                System.out.println("Dresseur");
            }
        }

        final Command AnimalCommand = new Command("Animal") {
            public void actionPerformed(ActionEvent evt) {
                afficherFicheDeDressageGUI aj = new afficherFicheDeDressageGUI();
                aj.getHi().show();
            }
        };

        hi.addCommand(AnimalCommand);

        final Command FAQCommand = new Command("F.A.Q") {
            public void actionPerformed(ActionEvent evt) {
                FaqClient faq = new FaqClient();
                faq.getHi().show();
            }
        };

        hi.addCommand(FAQCommand);

        final Command AffStatCommand = new Command("Afficher Stat Concours") {
            public void actionPerformed(ActionEvent evt) {
                Statistiques faq = new Statistiques();
                faq.createPieChartForm().show();
            }
        };

        hi.addCommand(AffStatCommand);

    }

    public Form getHi() {
        return hi;
    }

}
