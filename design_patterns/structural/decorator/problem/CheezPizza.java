package design_patterns.structural.decorator.problem;

public class CheezPizza extends BasicPizza {

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheeze";
    }

    @Override
    public double getCost() {
        return super.getCost() + 2.0;
    }
}
