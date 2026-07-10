package design_patterns.behavioural.state.problem;


enum TransportationMode {
    WALKING, CYCLING, CAR, TRAIN
}

public class DirectionService {

    private TransportationMode mode;

    public DirectionService(TransportationMode mode) {
        this.mode = mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    public int getETA() {
        return switch (mode) {
            case WALKING -> {
                System.out.println("Calc ETA for walking");
                yield 30;
            }
            case CYCLING -> {
                System.out.println("Calc ETA for cycling");
                yield 5;
            }
            case CAR -> {
                System.out.println("Calc ETA for car");
                yield 2;
            }
            case TRAIN -> {
                System.out.println("Calc ETA for train");
                yield 1;
            }
        };
    }

    public String getDirection() {
        return switch (mode) {
            case WALKING -> "Calc Direction for walking";
            case CYCLING -> "Calc Direction for cycling";
            case CAR -> "Calc Direction for car";
            case TRAIN -> "Calc Direction for train";
        };
    }


}
