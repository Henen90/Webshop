package db;

import bo.Order;
import bo.OrderStatus;
import bo.ShoppingCart;
import bo.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends bo.Order {

    protected OrderDAO (int orderId, String user, ShoppingCart shoppingCart, OrderStatus status) {
        super(orderId, user, shoppingCart, status);
    }

    public static boolean createOrder(String username, ShoppingCart shoppingCart, OrderStatus status) throws SQLException, IOException {
        String sqlOrder = "INSERT INTO ORDERS (USERNAME, STATUS) VALUES (?,?)";
        String sqlOrderItem = "INSERT INTO ORDER_ITEMS (ORDER_ID, PRODUCT_ID, PRICE) VALUES (?,?,?)";
        String sqlUpdateStock = "UPDATE PRODUCTS SET STOCK = STOCK -1 WHERE ID = ?";

        try (Connection connection = DBManager.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement stmtOrder = connection.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS);


            stmtOrder.setString(1, username);
            stmtOrder.setString(2, String.valueOf(status));
            stmtOrder.executeUpdate();


            ResultSet rs = stmtOrder.getGeneratedKeys();
            int orderId = -1;
            if(rs.next()){
                orderId = rs.getInt(1);
            }
            if(orderId == -1){
                System.out.println("Order_Id = -1");
                connection.rollback();
                throw new SQLException("Kunde inte hämta Order_Id");
            }

            PreparedStatement stmtOrderItem = connection.prepareStatement(sqlOrderItem);
            PreparedStatement stmtUpdateStock = connection.prepareStatement(sqlUpdateStock);

            for(var product : shoppingCart.getProducts()){
                stmtOrderItem.setInt(1, orderId);
                stmtOrderItem.setInt(2, product.getId());
                stmtOrderItem.setFloat(3, product.getPrice());
                stmtOrderItem.addBatch();

                stmtUpdateStock.setInt(1, product.getId());
                stmtUpdateStock.addBatch();
            }

            stmtOrderItem.executeBatch();
            stmtUpdateStock.executeBatch();
            connection.commit();

            return true;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT ORDER_ID, USERNAME, STATUS FROM ORDERS";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int orderId = rs.getInt("ORDER_ID");
                String username = rs.getString("USERNAME");
                String statusStr = rs.getString("STATUS");

                OrderStatus status = OrderStatus.valueOf(statusStr);

                Order order = new OrderDAO(
                        orderId,
                        username,
                        null,
                        status
                );

                orders.add(order);
            }
        }
        return orders;
    }

    public static boolean updateOrderStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE ORDERS SET STATUS = ? WHERE ORDER_ID = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }
}