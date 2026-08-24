package design_patterns.structural.decorator.solution;

public class CheeseDecorator extends PizzaDecorator {


    public CheeseDecorator(Pizza pizzaDecorator) {
        super(pizzaDecorator);
    }

    public String getDescription() {
        return pizzaDecorator.getDescription() + ", cheese";
    }

    public double getCost() {
        return pizzaDecorator.getCost() + 2.0;
    }

}
