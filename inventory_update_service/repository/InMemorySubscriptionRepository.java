package inventory_update_service.repository;

import inventory_update_service.entity.Subscription;
import inventory_update_service.enums.EventType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySubscriptionRepository implements SubscriptionRepository {

    private final Map<String, Subscription> store = new ConcurrentHashMap<>();

    @Override
    public void save(Subscription s) {
        store.put(s.getSubscriptionId(), s);
    }

    @Override
    public Optional<Subscription> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Subscription> findActiveByProductAndEventType(
            String productId, EventType eventType) {
        return store.values().stream()
                .filter(s -> s.getProductId().equals(productId))
                .filter(s -> s.covers(eventType)) // covers() checks ACTIVE status + event type set
                .toList();
    }

    @Override
    public List<Subscription> findByUserId(String userId) {
        return store.values().stream()
                .filter(s -> s.getUserId().equals(userId))
                .toList();
    }
}
