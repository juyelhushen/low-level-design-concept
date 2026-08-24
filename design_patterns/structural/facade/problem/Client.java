package design_patterns.structural.facade.problem;

public class Client {

    public static void main(String[] args) {
         UserService userService = new UserService();
         OrderService orderService = new OrderService();
         PaymentService paymentService = new PaymentService();

         userService.getUserDetails("1");
         orderService.processOrder("23");
         paymentService.paying(30.0);
    }
}
