package bo;

import db.OrderDAO;
import db.ProductDAO;
import db.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOFacade {

    public static UserDTO validateUser(String username, String password) throws SQLException {
        User user = UserDAO.findByUsernameAndPassword(username, password);
        if(user != null){
            return new UserDTO(user.getId(), user.getUserName(), user.geteMail(), user.getRole());
        }
        return null;
    }

    public static UserDTO getUser(String username) throws SQLException {
        User user = UserDAO.findByUsername(username);
        if(user != null){
            return new UserDTO(user.getId(), user.getUserName(), user.geteMail(), user.getRole());
        }
        return null;
    }

    public static List<UserDTO> getAllUsers() throws SQLException {
        List<User> users = UserDAO.getAllUsers();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getUserName(),
                    user.geteMail(),
                    user.getRole()
            ));
        }
        return userDTOS;
    }

    public static List<ProductDTO> getAllProducts() throws SQLException {
        List<Product> products = ProductDAO.getAllProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(int i = 0; i < products.size(); i++){
            productDTOS.add(new ProductDTO(products.get(i).getId(), products.get(i).getName(),products.get(i).getDescr(),products.get(i).getCategory(),products.get(i).getPrice(),products.get(i).getStock()));
        }
        return productDTOS;
    }

    public static boolean registerUser(String firstName, String lastName, String userName, String passWord, String eMail, String role) throws SQLException {
        return UserDAO.registerUser(firstName,lastName,userName,passWord,eMail, role);
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

    public static boolean deleteUser(int userId) throws SQLException {
        return UserDAO.deleteUser(userId);
    }

    public static boolean changeUserRole(int userId, String newRole) throws SQLException {
        return UserDAO.changeUserRole(userId, newRole);
    }

    public static boolean addProduct(String name, String descr, String category, float price, int stock) throws SQLException {
        return ProductDAO.addProduct(name, descr, category, price, stock);
    }

    public static boolean updateProduct(int id, String name, String descr, String category, float price, int stock) throws SQLException {
        return ProductDAO.updateProduct(id, name, descr, category, price, stock);
    }

    public static boolean removeProduct(int id) throws SQLException {
        return ProductDAO.removeProduct(id);
    }

    public static boolean updateOrderStatus(int orderId, String newStatus) throws SQLException {
        return OrderDAO.updateOrderStatus(orderId, newStatus);
    }

    public static List<OrderDTO> getAllOrders() throws SQLException {
        List<Order> orders = OrderDAO.getAllOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders) {
            UserDTO userDTO = getUser(order.getUser());
            orderDTOS.add(new OrderDTO(
                    order.getOrderId(),
                    userDTO,
                    order.getStatus().name(),
                    order.getProducts()
            ));
        }
        return orderDTOS;
    }
}