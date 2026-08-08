package design_patterns.behavioural.strategy;

public class WithoutStrategyPattern {

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment("Credit Card");
    }
}

class PaymentService {

    public void processPayment(String paymentMethod) {
        if (paymentMethod.equals("Credit Card"))
            System.out.println("Making Payment via credit card");
        else if (paymentMethod.equals("Debit card"))
            System.out.println("Making payment via debit card");
        else if (paymentMethod.equals("UPI"))
            System.out.println("Making payment via upi..");
        else
            System.out.println("Unsupported payment service!");
    }

}
