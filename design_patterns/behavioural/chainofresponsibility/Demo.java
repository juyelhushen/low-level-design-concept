package design_patterns.behavioural.chainofresponsibility;

public class Demo {

    public static void main(String[] args) {
        TeamLead lead = new TeamLead();
        Manager manager = new Manager();
        Director director = new Director();

        lead.setNext(manager);
        manager.setNext(director);

        LeaveRequest request = new LeaveRequest(5);
        lead.handleRequest(request);
    }


}
