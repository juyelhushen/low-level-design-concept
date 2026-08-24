package design_patterns.structural.decorator.problem;

public class BasicPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Basic pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}
