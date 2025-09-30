package bo;

import java.util.List;

public class ShoppingCart {
    private List<ProductDTO> products;
    private int index;

    public ShoppingCart(){
    }

    public boolean addToCart(ProductDTO product){
        return products.add(product);
    }

    public boolean removeFromCart(int index){
        if(index<products.size()){
            products.remove(index);
            return true;
        }
        System.out.printf("Index out of bounds");
        return false;
    }
}
