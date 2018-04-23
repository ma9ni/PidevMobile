/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui.Adoption;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.esprit.services.Adoption.AdoptionService;
import com.esprit.zanimo.Bar;

/**
 *
 * @author user
 */
public class AffichageAdoptionGui extends Bar{
    
  //   Form f;
    SpanLabel lb;
        public AffichageAdoptionGui() {
        super();
       // f = new Form();
        lb = new SpanLabel("");
        hi.add(lb);
            AdoptionService serviceTask=new AdoptionService();
        lb.setText(""+serviceTask.getList2().get(0).getIdAdoption());
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/

      
         
    }

    
}
