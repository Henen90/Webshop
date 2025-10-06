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

    protected ProductDAO(int id, String name, String descr, String category, float price, int stock) {
        super(id, name, descr, category, price, stock);
    }

    public static List<Product> searchProducts(String criteria){
        return null;
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, descr, category, price, stock FROM products";

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String descr = rs.getString("descr");
                String category = rs.getString("category");
                float price = rs.getFloat("price");
                int stock = rs.getInt(("stock"));

                ProductDAO product = new ProductDAO(id, name, descr, category, price, stock);
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
                int stock = rs.getInt("stock");
                product = new ProductDAO(id, name, descr, category, price, stock);
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

    public static boolean addProduct(String name, String descr, String category, float price, int stock) throws SQLException {
        String sql = "INSERT INTO PRODUCTS (NAME, DESCR, CATEGORY, PRICE, STOCK) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, descr);
            stmt.setString(3, category);
            stmt.setFloat(4, price);
            stmt.setInt(5, stock);

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean updateProduct(int id, String name, String descr, String category, float price, int stock) throws SQLException {
        String sql = "UPDATE PRODUCTS SET NAME = ?, DESCR = ?, CATEGORY = ?, PRICE = ?, STOCK = ? WHERE ID = ?";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, descr);
            stmt.setString(3, category);
            stmt.setFloat(4, price);
            stmt.setInt(5, stock);
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean removeProduct(int id) throws SQLException {
        String sql = "DELETE FROM PRODUCTS WHERE ID = ?";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }


}
