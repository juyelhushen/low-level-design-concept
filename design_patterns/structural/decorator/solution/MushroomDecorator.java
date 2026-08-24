package design_patterns.structural.decorator.solution;

public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizzaDecorator) {
        super(pizzaDecorator);
    }

    public String getDescription() {
        return pizzaDecorator.getDescription() + ", mushrooms";
    }

    public double getCost() {
        return pizzaDecorator.getCost() + 1.5;
    }
}
