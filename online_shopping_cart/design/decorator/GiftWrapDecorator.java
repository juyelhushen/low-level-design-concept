package online_shopping_cart.design.decorator;

public class GiftWrapDecorator extends CartItemDecorator {


    private static final double GIFT_WRAP_PRICE_PAISE = 5.0; // ₹5 per item

    public GiftWrapDecorator(CartItemComponent cartItemComponent) {
        super(cartItemComponent);
    }

    @Override
    public double getPrice() {
        return cartItemComponent.getPrice()  + GIFT_WRAP_PRICE_PAISE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Gift Wrap (₹5)";
    }
}
