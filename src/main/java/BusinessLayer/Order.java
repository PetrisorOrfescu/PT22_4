package BusinessLayer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private String clientUsername;
    private LocalDateTime orderDate;
    private List<BaseProduct> orderedItems;
    private double totalPrice;

    public Order(int orderId, String clientUsername, LocalDateTime orderDate, List<BaseProduct> orderedItems, double totalPrice) {
        this.orderId = orderId;
        this.clientUsername = clientUsername;
        this.orderDate = orderDate;
        this.orderedItems = orderedItems;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientUsername='" + clientUsername + '\'' +
                ", orderDate=" + orderDate +
                ", orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<BaseProduct> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<BaseProduct> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
