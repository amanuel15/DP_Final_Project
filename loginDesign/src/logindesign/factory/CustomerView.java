
package logindesign.factory;

// Concrete creator which overides the View abstract 

public class CustomerView extends ViewAbstract{

    public CustomerView(){getView();}
    
    @Override
    public IView getView() {
        return new CustomerButton();
    }
    
}
