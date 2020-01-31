
package logindesign.factory;

import logindesign.views.AdminJFrame;

public class AdminButton implements IView {

    
    public AdminButton(){onClickLogin();}
    @Override
    public void onClickLogin() {
        System.out.println("at admin button");
        // Display admin page
        AdminJFrame adminView = new AdminJFrame();
        adminView.setVisible(true);
        adminView.pack();
        adminView.setLocationRelativeTo(null);
    }

    @Override
    public void renderView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
