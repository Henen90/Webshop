package db;

import bo.Product;
import bo.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends bo.Product{

    protected ProductDAO(int id, String name, String descr, String category, float price) {
        super(id, name, descr, category, price);
    }

    public static List<Product> searchProducts(String criteria){
        return null;
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, descr, category, price FROM products";

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String descr = rs.getString("descr");
                String category = rs.getString("category");
                float price = rs.getFloat("price");

                ProductDAO product = new ProductDAO(id, name, descr, category, price);
                products.add(product);
            }
        }

        return products;
    }

    public static ProductDAO findProductById(int id) throws SQLException {
        String sql = "SELECT * FROM PRODUCTS WHERE id=?;";
        ProductDAO product=null;
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String descr = rs.getString("descr");
                String category = rs.getString("category");
                float price = rs.getFloat("price");
                product = new ProductDAO(id, name, descr, category, price);
            }
        }
        return product;
    }

    public static boolean addProduct(String name, int id, String descr){
        return false;
    }

    public static boolean remove(String name){
        return true;
    }
}
