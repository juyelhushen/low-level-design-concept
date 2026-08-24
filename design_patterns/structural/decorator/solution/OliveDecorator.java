package design_patterns.structural.decorator.solution;

public class OliveDecorator extends PizzaDecorator {


    public OliveDecorator(Pizza pizzaDecorator) {
        super(pizzaDecorator);
    }

    public String getDescription() {
        return pizzaDecorator.getDescription() + ". Olive";
    }

    public double getCost() {
        return pizzaDecorator.getCost() + 1.0;
    }
}
