package inventory_update_service.observer;

import inventory_update_service.entity.Subscription;
import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.events.*;
import inventory_update_service.repository.SubscriptionRepository;
import inventory_update_service.repository.UserRepository;
import inventory_update_service.service.NotificationService;

import java.util.List;

public class NotificationDispatcher implements InventoryObserver {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public NotificationDispatcher(SubscriptionRepository subscriptionRepository,
                                  UserRepository userRepository,
                                  NotificationService notificationService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void onEvent(InventoryEvent event) {
        // Pattern matching switch on the sealed interface — exhaustive by definition.
        // No default arm: if a new event type is added to the sealed interface
        // without a case here, this file won't compile.
        EventType type = switch (event) {
            case BackInStockEvent e -> EventType.BACK_IN_STOCK;
            case OutOfStockEvent e -> EventType.OUT_OF_STOCK;
            case PriceDropEvent e -> EventType.PRICE_DROP;
            case LowStockEvent e -> EventType.LOW_STOCK;
        };

        // Build the human-readable title from event data using pattern matching
        String title = switch (event) {
            case BackInStockEvent e -> e.productName() + " is back in stock!";
            case OutOfStockEvent e  -> e.productName() + " just went out of stock.";
            case PriceDropEvent e   -> String.format("%.0f%% price drop on %s!",
                    e.dropPercent(), e.productName());
            case LowStockEvent e    -> "Only " + e.remainingStock()
                    + " left — " + e.productName();
        };

        String body = switch (event) {
            case BackInStockEvent e -> "Great news! " + e.availableQuantity()
                    + " units are now available. Order before they sell out.";
            case OutOfStockEvent e  -> "You had this item saved. It's now out of stock — "
                    + "subscribe to be notified when it returns.";
            case PriceDropEvent e   -> String.format("Was Rs.%.0f, now Rs.%.0f. Grab it now!",
                    e.oldPrice(), e.newPrice());
            case LowStockEvent e    -> "Stock is running low. Only "
                    + e.remainingStock() + " units remain.";
        };

        String actionUrl = "https://amazon.in/dp/" + event.productId();

        // find all active subscriptions for this product that cover this event type
        List<Subscription> subscriptions = subscriptionRepository
                .findActiveByProductAndEventType(event.productId(), type);

        for (Subscription subscription : subscriptions) {
            userRepository.findById(subscription.getUserId()).ifPresent(user -> {
                // effectiveChannels: subscription-level override, or user's global preference
                for (NotificationChannel channel : subscription.effectiveChannels(user)) {
                    notificationService.dispatch(
                            user, subscription.getProductId(),
                            type, channel, title, body, actionUrl);
                }
            });
        }
    }
}
