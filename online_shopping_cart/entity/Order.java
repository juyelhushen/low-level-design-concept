package online_shopping_cart.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final String orderId;
    private final String userId;
    private final List<OrderItem> items;
    private final double subTotal;
    private final double discount;
    private final double deliveryCharges;
    private final double total;
    private final Address deliveryAddress;
    private final String couponCode;
    private final LocalDateTime placedAt;
    private String paymentId;

    //todo
    //order state


    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public double getTotal() {
        return total;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.items = builder.items;
        this.subTotal = builder.subtotal;
        this.discount = builder.discount;
        this.deliveryCharges = builder.deliveryCharge;
        this.total = builder.total;
        this.deliveryAddress = builder.deliveryAddress;
        this.couponCode = builder.couponCode;
        this.placedAt = LocalDateTime.now();
    }

    public static class Builder {
        private final String orderId;
        private final String userId;
        private final List<OrderItem> items;
        private final double subtotal;
        private final double total;
        private final Address deliveryAddress;

        private double discount      = 0;
        private double deliveryCharge = 0;
        private String couponCode;

        public Builder(String orderId, String userId,
                       List<OrderItem> items, double subtotal,
                       double total, Address deliveryAddress) {
            this.orderId = orderId;
            this.userId = userId;
            this.items = items;
            this.subtotal = subtotal;
            this.total = total;
            this.deliveryAddress = deliveryAddress;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public  Builder deliveryCharge(double deliveryCharge) {
            this.deliveryCharge = deliveryCharge;
            return this;
        }

        public Builder couponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
