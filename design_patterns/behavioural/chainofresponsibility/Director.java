package design_patterns.behavioural.chainofresponsibility;

public class Director implements LeaveHandler {
    private LeaveHandler nextHandler;


    @Override
    public void setNext(LeaveHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        System.out.println("Director approve leave request.");
    }
}
