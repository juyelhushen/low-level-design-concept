package design_patterns.structural.decorator.solution;

public class PizzaApp {

    public static void main(String[] args) {
        Pizza pizza = new BasicPizza();

        pizza = new CheeseDecorator(pizza);
        pizza = new OliveDecorator(pizza);
        pizza = new MushroomDecorator(pizza);
        pizza = new TomatoTopping(pizza);

        System.out.println(pizza.getDescription() + " Cost: " + pizza.getCost());
    }
}
