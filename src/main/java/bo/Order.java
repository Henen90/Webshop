package bo;


public class Order {
    private int orderId;
    private String user;
    private ShoppingCart products;
    private OrderStatus status;

    public Order(int orderId, String username, ShoppingCart products, OrderStatus status) {
        this.orderId = orderId;
        this.user = username;
        this.products = products;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ShoppingCart getProducts() {
        return products;
    }

    public void setProducts(ShoppingCart products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
