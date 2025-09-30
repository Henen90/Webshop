package db;

import bo.Product;

import java.util.List;

public class ProductDAO extends bo.Product{

    protected ProductDAO(int id, String name, String descr) {
        super(id, name, descr);
    }

    public static List<Product> searchProducts(String criteria){
        return null;
    }

    public static List<Product> getAllProducts(){
        return null;
    }

    public static boolean addProduct(String name, int id, String descr){
        return false;
    }

    public static boolean remove(String name){
        return true;
    }
}
