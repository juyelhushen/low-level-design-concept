package design_patterns.structural.facade.solution;

public class OrderService {

    public String processingOrder(String orderId) {
        return String.format("Processing with Order ID: %s", orderId);
    }
}
