package online_shopping_cart.design.decorator;

import online_shopping_cart.entity.Product;

// Concrete component: just the product at its base price × quantity.
// No add-ons. This is what gets wrapped by decorators.
public class BaseCartItem implements CartItemComponent {

    private final Product product;
    private final int quantity;

    public BaseCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getProductId() {
        return product.getProductId();
    }

    @Override
    public String getProductName() {
        return product.getProductName();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String getDescription() {
        return product.getProductName() + " x " + quantity;
    }
}
