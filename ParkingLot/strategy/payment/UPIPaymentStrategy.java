package ParkingLot.strategy.payment;


public class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {
        System.out.printf("  [Payment] Collected Rs.%.2f in cash.%n", amount);
        return true;
    }

}
