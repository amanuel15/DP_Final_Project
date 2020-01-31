package logindesign.strategy;

public class DeductStrategy implements IBillingStrategy{

    private Double finalPrice;
    private Double price;
    
    @Override
    public Double calculate(Double price, Double discount) {
        this.price = price;
        System.out.println("perfromin: "+ price+" plsu "+ discount);
        return finalPrice = price - discount;
    }

    @Override
    public Double discountAmount(Double price) {
        return finalPrice - price;
    }
    
    
    
}
