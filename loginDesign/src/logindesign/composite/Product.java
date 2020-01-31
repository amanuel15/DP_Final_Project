
package logindesign.composite;

public class Product implements IProductsComposite {
    
    
    private String name; 
    private Double price; 
    private int amount;
    
    public Product(String name, Double price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public int getAmount() {
        return amount;
    }
    
}
