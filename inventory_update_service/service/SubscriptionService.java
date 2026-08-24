package inventory_update_service.service;

import inventory_update_service.entity.Subscription;
import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.repository.SubscriptionRepository;

import java.util.Set;

public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    // Subscribe to a product for specific event types using user's global channel preference
    public Subscription subscribe(String userId, String productId, Set<EventType> eventTypes) {
        return subscribe(userId, productId, eventTypes, Set.of()); // empty = use user preference
    }

    // Subscribe with explicit channel overrides for this subscription
    public Subscription subscribe(String userId,
                                     String productId,
                                     Set<EventType> eventTypes,
                                     Set<NotificationChannel> channels
                                     ) {
        Subscription subscription = new Subscription(userId, productId,
                eventTypes, channels);
        subscriptionRepository.save(subscription);
        return subscription;
    }

    public void unsubscribe(String subscriptionId) {
        subscriptionRepository.findById(subscriptionId)
                .ifPresent(Subscription::cancel);
    }
}
