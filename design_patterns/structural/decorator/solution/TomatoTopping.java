package design_patterns.structural.decorator.solution;

public class TomatoTopping extends PizzaDecorator {

    public TomatoTopping(Pizza pizzaDecorator) {
        super(pizzaDecorator);
    }

    public String getDescription() {
        return pizzaDecorator.getDescription() + ", tomato";
    }

    public double getCost() {
        return pizzaDecorator.getCost() + 1.0;
    }
}
