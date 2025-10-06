package bo;

import db.OrderDAO;
import db.ProductDAO;
import db.UserDAO;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOFacade {

    public static UserDTO validateUser(String username, String password) throws SQLException {
        User user = UserDAO.findByUsernameAndPassword(username, password);
        if(user != null){
            return new UserDTO(user.getUserName(), user.geteMail());
        }
        return null;
    }

    public static UserDTO getUser(String username) throws SQLException {
        User user = UserDAO.findByUsername(username);
        if(user != null){
            return new UserDTO(user.getUserName(), user.geteMail());
        }
        return null;
    }

    public static List<ProductDTO> getAllProducts() throws SQLException {
        List<Product> products = ProductDAO.getAllProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(int i = 0; i < products.size(); i++){
            productDTOS.add(new ProductDTO(products.get(i).getId(), products.get(i).getName(),products.get(i).getDescr(),products.get(i).getCategory(),products.get(i).getPrice(),products.get(i).getStock()));
        }
        return productDTOS;
    }

    public static boolean registerUser(String firstName, String lastName, String userName, String passWord, String eMail) throws SQLException {
        return UserDAO.registerUser(firstName,lastName,userName,passWord,eMail);
    }

    public static List<ProductDTO> getProductsByName(){
        return null;
    }

    public static List<ProductDTO> getProductsByCategory(){
        return null;
    }

    public static ProductDTO getProductById(int id) throws SQLException {
        Product product = ProductDAO.findProductById(id);
        if (product != null) return new ProductDTO(product.getId(), product.getName(),product.getDescr(),product.getCategory(), product.getPrice(), product.getStock());
        return null;
    }

    public static boolean putOrder(String user, ShoppingCart shoppingCart, OrderStatus status) throws SQLException, IOException {

        return OrderDAO.createOrder(user, shoppingCart, status);
    }

}
