package inventory_update_service.entity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private final String productId;
    private final String name;
    private final String category;
    private double price;
    private AtomicInteger stockQuantity;

    public static final int LOW_STOCK_THRESHOLD = 5;

    public Product(String name, String category,
                   double price, int stockQuantity) {
        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = new AtomicInteger(stockQuantity);
    }

    public boolean isInStock() {
        return this.stockQuantity.get() > 0;
    }

    public boolean isLowStock() {
        return this.stockQuantity.get() > 0 && this.stockQuantity.get() <= 5;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity.get();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity.set(stockQuantity);
    }

    // returns false if another thread changed qty since caller last read it
    public boolean compareAndSetStock(int expected, int newQty) {
        return stockQuantity.compareAndSet(expected, newQty);
    }
}
