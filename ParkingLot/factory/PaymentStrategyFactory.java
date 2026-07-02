package ParkingLot.factory;

import ParkingLot.enums.PaymentMode;
import ParkingLot.strategy.payment.CardPaymentStrategy;
import ParkingLot.strategy.payment.CashPaymentStrategy;
import ParkingLot.strategy.payment.PaymentStrategy;
import ParkingLot.strategy.payment.UPIPaymentStrategy;

public class PaymentStrategyFactory {

    public PaymentStrategyFactory() {
    }

    public static PaymentStrategy getStrategy(PaymentMode mode) {
        return switch (mode) {
            case UPI -> new UPIPaymentStrategy();
            case CARD -> new CardPaymentStrategy();
            case CASH -> new CashPaymentStrategy();
            default -> throw new IllegalArgumentException("Unknown Payment Mode");
        };
    }

}
