package inventory_update_service.service;

import inventory_update_service.entity.Product;
import inventory_update_service.events.*;
import inventory_update_service.observer.InventoryObserver;
import inventory_update_service.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Subject in the Observer pattern. Detects stock transitions and
// fires the right sealed event type. Never knows who's listening.
public class InventoryService {

    private final ProductRepository productRepository;

    // CopyOnWriteArrayList: observer registrations happen at startup (rare writes),
    // event firing happens on every stock update (frequent reads)
    private final List<InventoryObserver> observers = new CopyOnWriteArrayList<>();

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void registerObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * The core state-transition detector.
     * Compares old vs new stock to decide WHICH event to fire.
     * Only fires an event when the state actually changes — not on
     * every stock update (e.g. 10→8 is neither back-in-stock nor out-of-stock).
     */
    public void updateStock(String productId, int newQuantity) {
        Product product = findByProductId(productId);

        int oldQuantity = product.getStockQuantity();
        product.setStockQuantity(newQuantity);
        productRepository.save(product);

        LocalDateTime now = LocalDateTime.now();

        // transition: had stock → now empty
        if (oldQuantity > 0 && newQuantity == 0) {
            fire(new OutOfStockEvent(productId, product.getName(), now));
        }
        // transition: was empty → now has stock
        else if (oldQuantity == 0 && newQuantity > 0) {
            fire(new BackInStockEvent(productId, product.getName(), newQuantity, now));
        }
        // transition: was above threshold → now at or below threshold (but not zero)
        else if (
                newQuantity > 0
                        && newQuantity <= Product.LOW_STOCK_THRESHOLD
                        && oldQuantity > Product.LOW_STOCK_THRESHOLD
        ) {
            fire(new LowStockEvent(productId, product.getName(), newQuantity, now));
        }
        // all other changes (e.g. 50→30) are not interesting to subscribers
    }

    public void updatePrice(String productId, double newPrice) {
        Product product = findByProductId(productId);
        double oldPrice = product.getPrice();
        product.setPrice(newPrice);
        productRepository.save(product);

        LocalDateTime occurred = LocalDateTime.now();

        if (newPrice < oldPrice) {
            fire(new PriceDropEvent(productId, product.getName(), newPrice, oldPrice, occurred));
        }
    }

    private Product findByProductId(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    private void fire(InventoryEvent event) {
        System.out.printf("%n[Inventory] Event fired: %s for product: %s%n",
                event.getClass().getSimpleName(), event.productName());
        for (InventoryObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
