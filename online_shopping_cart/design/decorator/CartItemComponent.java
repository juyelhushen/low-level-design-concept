package online_shopping_cart.design.decorator;

/**
 * Component interface — both BaseCartItem and all decorators implement this.
 * The cart only ever talks to CartItemComponent — it never knows if an item
 * is plain or has 3 layers of add-ons stacked on top.
 */
public interface CartItemComponent {
    String getProductId();
    String getProductName();
    int getQuantity();
    double getPrice();
    String getDescription();
}
