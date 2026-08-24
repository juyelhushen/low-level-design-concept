package online_shopping_cart.design.decorator;

public class ExpressDeliveryDecorator extends CartItemDecorator {

    private static final double EXPRESS_PRICE_PAISE = 30.0; // ₹30

    public ExpressDeliveryDecorator(CartItemComponent cartItemComponent) {
        super(cartItemComponent);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + EXPRESS_PRICE_PAISE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Express Delivery (₹30)";
    }


}
