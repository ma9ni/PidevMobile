/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.users;

import com.codename1.io.AccessToken;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Dialog;
import com.esprit.services.user.UsersServices;

/**
 *
 * @author user
 */
    public class LoginListener extends LoginCallback {

        public static final int FACEBOOK = 0;

        public static final int GOOGLE = 1;

        private int loginType;

        public LoginListener(int loginType) {
            this.loginType = loginType;
        }

        public void loginSuccessful() {
            UsersServices l=new UsersServices();
            try {
                AccessToken token = Loginn.loginMethode.getAccessToken();
                if (loginType == FACEBOOK) {
                    l.showFacebookUser(token.getToken());
                } else if (loginType == GOOGLE) {
                   // l.showGoogleUser(token.getToken());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void loginFailed(String errorMessage) {
            Dialog.show("Login Failed", errorMessage, "Ok", null);
        }
    }

