package online_shopping_cart.design.decorator;


/**
 * Abstract decorator: delegates all methods to the wrapped component.
 * Concrete decorators only override what they change (price + description).
 *
 * The key structural rule: CartItemDecorator IS-A CartItemComponent (so it
 * can stand anywhere a component is expected) AND HAS-A CartItemComponent
 * (so it can wrap any component, including another decorator).
 */

public abstract class CartItemDecorator implements CartItemComponent {

    protected final CartItemComponent cartItemComponent;

    public CartItemDecorator(CartItemComponent cartItemComponent) {
        this.cartItemComponent = cartItemComponent;
    }

    @Override
    public String getProductId() {
        return cartItemComponent.getProductId();
    }

    @Override
    public String getProductName() {
        return cartItemComponent.getProductName();
    }

    @Override
    public int getQuantity() {
        return cartItemComponent.getQuantity();
    }

    @Override
    public double getPrice() {
        return cartItemComponent.getPrice();
    }

    @Override
    public String getDescription() {
        return cartItemComponent.getDescription();
    }
}
