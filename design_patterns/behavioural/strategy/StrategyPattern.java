package design_patterns.behavioural.strategy;

interface PaymentStrategy {
    void pay();
}

class DebitPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Making payment via debit card");
    }
}

class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Making payment via UPI");
    }
}

class PaymentStrategyContext {
    private PaymentStrategy paymentStrategy;

//    public PaymentStrategyContext(PaymentStrategy paymentStrategy) {
//        this.paymentStrategy = paymentStrategy;
//    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay() {
        paymentStrategy.pay();
    }
}

public class StrategyPattern {

    public static void main(String[] args) {
        PaymentStrategyContext context = new PaymentStrategyContext();
        context.setPaymentStrategy(new DebitPaymentStrategy());
        context.pay();
    }
}
