package design_patterns.structural.facade.solution;

public class PaymentService {

    public String paying(float amount) {
        return String.format("paying total amount %.2f", amount);
    }
}
