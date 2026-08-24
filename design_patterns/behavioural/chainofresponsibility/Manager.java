package design_patterns.behavioural.chainofresponsibility;

public class Manager implements LeaveHandler {

    private LeaveHandler nextHandler;

    @Override
    public void setNext(LeaveHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDays() <= 3) {
            System.out.println("Manager approved the leave request.");
        } else {
            nextHandler.handleRequest(request);
        }
    }
}
