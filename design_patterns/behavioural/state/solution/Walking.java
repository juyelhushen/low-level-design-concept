package design_patterns.behavioural.state.solution;

public class Walking implements TransportationMode {

    @Override
    public int calcETA() {
        System.out.println("Calculating ETA for walking...");
        return 15;
    }

    @Override
    public String getDirection() {
        return "Getting direction for walking..";
    }
}
