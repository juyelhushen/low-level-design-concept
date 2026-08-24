package online_shopping_cart.entity;

/**
 * Immutable snapshot of a cart line at checkout time.
 * Price is locked here — even if the product price changes later,
 * the order always shows what the customer was actually charged.
 */
public class OrderItem {
    private final String productId;
    private final String productName;
    private final double unitPricee;    // price per unit at checkout moment
    private final double totalPrice;   // unit * qty + add-ons
    private final String description;       // "iPhone 15 Pro + Gift Wrap + Warranty"

    public OrderItem(String productId, String productName,
                     double unitPricee, double totalPrice,
                     String description) {
        this.productId = productId;
        this.productName = productName;
        this.unitPricee = unitPricee;
        this.totalPrice = totalPrice;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPricee() {
        return unitPricee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDescription() {
        return description;
    }
}
