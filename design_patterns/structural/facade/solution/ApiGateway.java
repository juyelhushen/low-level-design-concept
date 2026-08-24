package design_patterns.structural.facade.solution;

public class ApiGateway {

    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public ApiGateway() {
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }

    public String getUserOrdersDetails(String userId, String orderId, float amount) {
        return String.format(
                "User: %s\n Order: %s\n Amount: %s",
                userService.getUserDetails(userId),
                orderService.processingOrder(orderId),
                paymentService.paying(amount)
        );
    }
}
