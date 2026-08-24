package design_patterns.structural.decorator.solution;

public class PizzaDecorator implements Pizza {

    protected final Pizza pizzaDecorator;

    public PizzaDecorator(Pizza pizzaDecorator) {
        this.pizzaDecorator = pizzaDecorator;
    }


    @Override
    public String getDescription() {
        return pizzaDecorator.getDescription();
    }

    @Override
    public double getCost() {
        return pizzaDecorator.getCost();
    }
}
