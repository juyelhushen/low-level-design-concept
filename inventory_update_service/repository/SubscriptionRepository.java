package inventory_update_service.repository;

import inventory_update_service.entity.Subscription;
import inventory_update_service.enums.EventType;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    void save(Subscription subscription);
    Optional<Subscription> findById(String subscriptionId);

    // core query: which users care about this product + this event type?
    List<Subscription> findActiveByProductAndEventType(String productId, EventType eventType);

    List<Subscription> findByUserId(String userId);
}
