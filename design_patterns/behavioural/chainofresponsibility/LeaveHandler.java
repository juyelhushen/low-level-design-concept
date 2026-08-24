package design_patterns.behavioural.chainofresponsibility;

public interface LeaveHandler {
    void setNext(LeaveHandler handler);
    void handleRequest(LeaveRequest request);
}
