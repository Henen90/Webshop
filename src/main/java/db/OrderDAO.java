package db;

import bo.OrderStatus;
import bo.ShoppingCart;
import bo.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends bo.Order {

    protected OrderDAO (int orderId, String user, ShoppingCart shoppingCart, OrderStatus status) {
        super(orderId, user, shoppingCart, status);
    }

    public static boolean createOrder(String username, ShoppingCart shoppingCart, OrderStatus status) throws SQLException, IOException {
        String sqlOrder = "INSERT INTO ORDERS (USERNAME, STATUS) VALUES (?,?)";
        String sqlOrderItem = "INSERT INTO ORDER_ITEMS (ORDER_ID, PRODUCT_ID, PRICE) VALUES (?,?,?)";

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement stmtOrder = connection.prepareStatement(sqlOrder);
            PreparedStatement stmtOrderItem = connection.prepareStatement(sqlOrderItem);

            stmtOrder.setString(1, username);
            stmtOrder.setString(2, String.valueOf(status));
            stmtOrder.executeUpdate();



            //ResultSet rs = sqlOrder.
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
