/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindesign;

import logindesign.views.LoginJFrame;

/**
 *
 * @author aman
 */
public class LoginDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginDesign design = new LoginDesign();
        design.initialize();
    }
    public void initialize(){
        LoginJFrame login = new LoginJFrame();
        login.setVisible(true);
    }
    
}
