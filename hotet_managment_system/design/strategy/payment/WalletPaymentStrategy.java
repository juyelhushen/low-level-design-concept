package hotet_managment_system.design.strategy.payment;

public class WalletPaymentStrategy implements PaymentStrategy {
    public static final WalletPaymentStrategy INSTANCE = new WalletPaymentStrategy();

    private WalletPaymentStrategy() {}

    @Override
    public boolean pay(double amount) {
        System.out.printf(" [Payment] Rs.%.2f paid via Wallet.%n", amount);
        return true;
    }
}
