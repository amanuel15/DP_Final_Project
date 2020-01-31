package logindesign.state;

public class StateContext {
    private IState state;
    
    public StateContext(){
        state = null;
    }
    
    public void setState(IState state){
        this.state = state;
    }
    
    public IState getState(){
        return state;
    }
    
}
