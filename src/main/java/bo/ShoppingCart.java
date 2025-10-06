package bo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ProductDTO> products;

    public ShoppingCart(){
        this.products = new ArrayList<>();
    }

    public void addToCart(ProductDTO product){
        products.add(product);
    }

    public List<ProductDTO> getProducts() {
        return this.products;
    }

    public boolean removeFromCart(int index){
        if(index<products.size()){
            products.remove(index);
            return true;
        }
        System.out.print("Index out of bounds");
        return false;
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
