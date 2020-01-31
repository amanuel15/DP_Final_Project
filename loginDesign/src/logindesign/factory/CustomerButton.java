
package logindesign.factory;

import logindesign.views.CustomerJFrame;

public class CustomerButton implements IView{

    public CustomerButton(){onClickLogin();}
    
    @Override
    public void onClickLogin() {
        // Display customer page
        CustomerJFrame customerView = new CustomerJFrame();
        customerView.setVisible(true);
        customerView.pack();
        customerView.setLocationRelativeTo(null);
    }

    @Override
    public void renderView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
