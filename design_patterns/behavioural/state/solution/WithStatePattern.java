package design_patterns.behavioural.state.solution;


public class WithStatePattern {

    public static void main(String[] args) {
        DirectionService service = new DirectionService(new Train());
        service.setMode(new Cycling());

        System.out.println("ETA : " + service.getETA());
        System.out.println("Direction : " + service.getDirection());
    }
}
