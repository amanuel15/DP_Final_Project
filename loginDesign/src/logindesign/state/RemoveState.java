package logindesign.state;

public class RemoveState implements IState {

    @Override
    public void updateCart(StateContext stateContext) {
        
        stateContext.setState(this);
        
    }
    
    public String stateName(){
        return "remove";
    }
    
}
