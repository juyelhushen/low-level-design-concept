package hotet_managment_system.entity;

import hotet_managment_system.enums.PaymentMode;
import hotet_managment_system.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    private final String paymentId;
    private final String bookingId;
    private final double amount;
    private final PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private final LocalDateTime initiatedTime;


    public Payment(String bookingId, double amount, PaymentMode paymentMode, PaymentStatus paymentStatus) {
        this.paymentId = java.util.UUID.randomUUID().toString();
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.initiatedTime = LocalDateTime.now();
        this.paymentStatus = paymentStatus;
    }


    public void markCompleted() { this.paymentStatus = PaymentStatus.COMPLETED; }
    public void markFailed()    { this.paymentStatus = PaymentStatus.FAILED; }
    public void markRefunded()  { this.paymentStatus = PaymentStatus.REFUNDED; }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

}
