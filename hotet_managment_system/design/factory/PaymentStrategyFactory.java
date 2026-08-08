package hotet_managment_system.design.factory;

import hotet_managment_system.design.strategy.payment.CreditCardPaymentStrategy;
import hotet_managment_system.design.strategy.payment.PaymentStrategy;
import hotet_managment_system.design.strategy.payment.UPIPaymentStrategy;
import hotet_managment_system.design.strategy.payment.WalletPaymentStrategy;
import hotet_managment_system.enums.PaymentMode;

public class PaymentStrategyFactory {

    private PaymentStrategyFactory() {
    }

    public PaymentStrategy paymentStrategy(PaymentMode mode) {
        return (PaymentStrategy) switch (mode) {
            case CARD -> CreditCardPaymentStrategy.INSTANCE;
            case UPI -> UPIPaymentStrategy.INSTANCE;
            case WALLET -> WalletPaymentStrategy.INSTANCE;
        };
    }
}
