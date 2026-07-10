package design_patterns.behavioural.state.solution;

public class Train implements TransportationMode {

    @Override
    public int calcETA() {
        System.out.println("Calculating ETA for train...");
        return 5;
    }

    @Override
    public String getDirection() {
        return "Getting direction for train...";
    }
}
