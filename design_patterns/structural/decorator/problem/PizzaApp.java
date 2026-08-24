package design_patterns.structural.decorator.problem;

public class PizzaApp {

    public static void main(String[] args) {
        Pizza pizza = new CheezeOlivePizza();
        System.out.println(pizza.getDescription() + " Cost: " + pizza.getCost());

    }
}

//Problems
//Scalability -- many more combination and classes
//Maintainability and Testing
