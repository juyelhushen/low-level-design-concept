package design_patterns.structural.facade.problem;

public class OrderService {

    public void processOrder(String orderId) {
        System.out.printf("processOrder(): orderId: %s\n", orderId);
    }
}
