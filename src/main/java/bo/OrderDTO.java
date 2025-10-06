package bo;

public class OrderDTO {
    private int orderId;
    private UserDTO user;
    private ShoppingCart order;
    private String status;

    public OrderDTO(int orderId, UserDTO user, String status, ShoppingCart order) {
        this.orderId = orderId;
        this.user = user;
        this.status = status;
        this.order = order;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ShoppingCart getOrder() {
        return order;
    }

    public void setOrder(ShoppingCart order) {
        this.order = order;
    }

    public String getStatus() { return status; }
}
