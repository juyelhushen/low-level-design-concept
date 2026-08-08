package hotet_managment_system.design.strategy.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    public static final CreditCardPaymentStrategy INSTANCE = new CreditCardPaymentStrategy();

    private CreditCardPaymentStrategy() {}

    @Override
    public boolean pay(double amount) {
        System.out.printf(" [Payment] Rs.%.2f charged to credit card.%n", amount);
        return true;
    }
}
