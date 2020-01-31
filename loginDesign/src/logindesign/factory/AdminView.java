
package logindesign.factory;

// Concrete creator which overides the View abstract 

public class AdminView extends ViewAbstract {

    public AdminView(){getView();}
    @Override
    public IView getView() {
        return new AdminButton();
    }
    
}
