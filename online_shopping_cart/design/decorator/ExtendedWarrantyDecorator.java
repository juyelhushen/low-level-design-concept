package online_shopping_cart.design.decorator;

public class ExtendedWarrantyDecorator extends CartItemDecorator {

    private static final double WARRANTY_PRICE_PAISE = 50; // ₹500

    public ExtendedWarrantyDecorator(CartItemComponent cartItemComponent) {
        super(cartItemComponent);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + WARRANTY_PRICE_PAISE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Extended Warranty (₹500)";
    }
}
