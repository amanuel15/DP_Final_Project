package logindesign.composite;

import java.util.List;
import java.util.ArrayList;

public class CartComposite implements IProductsComposite {

    public List<Product> productsList = new ArrayList<Product>();
    
    @Override
    public Double getPrice() {
         Double sum = 0.0;
         for (int i = 0; i < productsList.size(); i++) {
             sum += productsList.get(i).getPrice()*productsList.get(i).getAmount();
         }
         return sum;
    }

    @Override
    public int getAmount() {
        int sum = 0;
         for (int i = 0; i < productsList.size(); i++) {
             sum += productsList.get(i).getAmount();
         }
        return sum;
    }
    
    public void addProduct(Product product) 
    { 
        productsList.add(product);
    } 
       
    public void removeProduct(int product) 
    { 
        productsList.remove(product);
    }
    
}
