package ParkingLot.entity;

import ParkingLot.enums.PaymentMode;
import ParkingLot.enums.PaymentStatus;

import java.util.UUID;

public class Payment {

    private final String paymentId;
    private final String ticketId;
    private final double amount;
    private final PaymentMode mode;
    private PaymentStatus status;

    public Payment(String ticketId, double amount, PaymentMode mode) {
        this.paymentId = UUID.randomUUID().toString();
        this.ticketId = ticketId;
        this.amount = amount;
        this.mode = mode;
        this.status = PaymentStatus.PENDING;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getTicketId() {
        return ticketId;
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

    public void markCompleted() {
        this.status = PaymentStatus.COMPLETED;
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
    }
}
