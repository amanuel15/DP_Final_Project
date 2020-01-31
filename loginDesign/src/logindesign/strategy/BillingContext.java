package logindesign.strategy;

public class BillingContext {
    
    private IBillingStrategy strategy;
    
    public BillingContext(IBillingStrategy strategy){
        this.strategy = strategy;
    }
    
    public Double executeStrategy(Double price, Double discount){
        
        return strategy.calculate(price, discount);
    }
    
}
