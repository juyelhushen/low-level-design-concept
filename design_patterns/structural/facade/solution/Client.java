package design_patterns.structural.facade.solution;

public class Client {

    public static void main(String[] args) {
        ApiGateway request = new ApiGateway();
        System.out.println(request.getUserOrdersDetails("123", "31", 45.0f));
    }
}
