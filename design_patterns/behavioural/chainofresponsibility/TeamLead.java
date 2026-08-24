package design_patterns.behavioural.chainofresponsibility;

public class TeamLead implements LeaveHandler {

    private LeaveHandler nextHandler;

    @Override
    public void setNext(LeaveHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDays() <= 2) {
            System.out.println("Team Lead approved the leave request.");
        }else {
            nextHandler.handleRequest(request);
        }
    }
}
