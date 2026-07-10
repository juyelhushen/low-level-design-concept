package design_patterns.behavioural.state.problem;

public class WithOutStatePattern {

    public static void main(String[] args) {
        DirectionService service = new DirectionService(TransportationMode.TRAIN);
        service.setMode(TransportationMode.CYCLING);

        System.out.println(service.getDirection());
        System.out.println(service.getETA());
    }
}


//problem
//As more states and actions are added, the code becomes:

//Hard to read
//Hard to maintain
//Difficult to extend
//Violates the Open/Closed Principle because adding a new state requires modifying existing code