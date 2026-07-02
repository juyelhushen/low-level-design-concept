package ParkingLot.context;

import ParkingLot.entity.Payment;
import ParkingLot.enums.PaymentMode;
import ParkingLot.strategy.payment.PaymentStrategy;

public class PaymentStrategyContext {

    private PaymentStrategy paymentStrategy;


    public PaymentStrategyContext() {
    }

    public PaymentStrategyContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Payment processPayment(String ticketId, double amount, PaymentMode mode) {
        Payment payment = new Payment(ticketId, amount, mode);
        boolean success = paymentStrategy.pay(amount);

        if (success) payment.markCompleted();
        else payment.markFailed();

        return payment;
    }
}
