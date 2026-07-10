package design_patterns.behavioural.state.solution;

public class DirectionService {
    TransportationMode mode;

    public DirectionService(TransportationMode mode) {
        this.mode = mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    //delegating the current state's to concrete class
    public int getETA() {
        return mode.calcETA();
    }

    public String getDirection() {
        return mode.getDirection();
    }
}
