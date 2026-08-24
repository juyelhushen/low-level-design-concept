package online_shopping_cart.entity;

import online_shopping_cart.design.decorator.CartItemComponent;

import java.time.LocalDateTime;
import java.util.*;

public class Cart {

    private final String cartId;
    private final String userId;

    private final Map<String, CartItemComponent> items = new LinkedHashMap<>();
    private LocalDateTime updatedAt;

    public Cart(String cartId, String userId) {
        this.cartId = cartId;
        this.userId = userId;
        this.updatedAt = LocalDateTime.now();
    }

    public void addItem(CartItemComponent item) {
        items.put(item.getProductId(), item);
        updatedAt = LocalDateTime.now();
    }

    public void removeItem(String productId) {
        items.remove(productId);
        updatedAt = LocalDateTime.now();
    }

    public CartItemComponent getItem(String productId) {
        return items.get(productId);
    }

    public boolean hasItem(String productId) {
        return items.containsKey(productId);
    }

    public Collection<CartItemComponent> getItems() {
        return items.values();
    }

    public double getSubTotalAmount() {
        return items.values().stream()
                .mapToDouble(CartItemComponent::getPrice)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public String getCartId() {
        return cartId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
