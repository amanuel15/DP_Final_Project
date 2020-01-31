package logindesign.state;

public class AddState implements IState{

    @Override
    public void updateCart(StateContext stateContext) {
        
        stateContext.setState(this);
        
    }
    
    public String stateName(){
        return "add";
    }
    
}
