package online_shopping_cart.entity;

import online_shopping_cart.enums.PaymentMode;
import online_shopping_cart.enums.PaymentStatus;

import java.util.UUID;

public class Payment {

    private final String paymentId;
    private final String orderId;
    private final double amount;
    private final PaymentMode mode;
    private PaymentStatus status;

    public Payment(String orderId, double amount, PaymentMode mode) {
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.mode = mode;
    }

    public void markCompleted() {
        status = PaymentStatus.COMPLETED;
    }

    public void markFailed() {
        status = PaymentStatus.FAILED;
    }

    public void markRefunded() {
        status = PaymentStatus.REFUNDED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
