package design_patterns.behavioural.state.solution;

public class Cycling implements TransportationMode {

    @Override
    public int calcETA() {
        System.out.println("Calculating ETA for cycling...");
        return 10;
    }

    @Override
    public String getDirection() {
        return "Getting direction for cycling...";
    }
}
