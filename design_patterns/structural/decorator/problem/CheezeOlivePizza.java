package design_patterns.structural.decorator.problem;

public class CheezeOlivePizza extends CheezPizza {
    @Override
    public String getDescription() {
        return super.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
