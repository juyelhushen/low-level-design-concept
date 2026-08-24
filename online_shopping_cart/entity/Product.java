package online_shopping_cart.entity;

import online_shopping_cart.enums.ProductCategory;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private final String productId;
    private final String productName;
    private final String productBrand;
    private final ProductCategory category;
    private final double price;
    private final AtomicInteger stockQuantity;

    public Product(String productName, String productBrand,
                   ProductCategory category, double price, int stockQuantity) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productBrand = productBrand;
        this.category = category;
        this.price = price;
        this.stockQuantity = new AtomicInteger(stockQuantity);
    }


    /**
     * CAS-based reservation: atomically decrements stock.
     * Returns false if stock is insufficient — caller handles gracefully.
     * Same pattern as ParkingSpot.tryOccupy() and Splitwise's borrow count.
     */

    public boolean reserve(int quantity) {
        int current;

        do {
            current = stockQuantity.get();
            if (current < quantity) return false;
        } while (!stockQuantity.compareAndSet(current, current - quantity));

        return true;
    }

    public int release(int quantity) {
        return stockQuantity.addAndGet(quantity);  // restore on payment failure or cancellation
    }


    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public AtomicInteger getStockQuantity() {
        return stockQuantity;
    }
}
