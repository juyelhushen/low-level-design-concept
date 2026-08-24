package design_patterns.structural.decorator.solution;

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
