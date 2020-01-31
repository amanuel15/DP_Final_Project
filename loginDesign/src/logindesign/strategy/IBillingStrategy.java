package logindesign.strategy;

public interface IBillingStrategy {
    
    public Double calculate(Double price, Double discount);
    public Double discountAmount(Double price);
    
}
