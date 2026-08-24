package inventory_update_service.entity;

import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.enums.SubscriptionStatus;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

// A user subscribes to a product for specific event types.
// e.g. Alice subscribes to iPhone 15 for BACK_IN_STOCK and PRICE_DROP only.
// channel override: if empty, falls back to user's global preferred channels.
public class Subscription {
    private final String subscriptionId;
    private final String userId;
    private final String productId;
    private final Set<EventType> eventTypes;
    private final Set<NotificationChannel> channelOverrides;
    private SubscriptionStatus subscriptionStatus;
    private final LocalDateTime subscribedAt;

    public Subscription(String userId, String productId,
                        Set<EventType> eventTypes,
                        Set<NotificationChannel> channelOverrides) {
        this.subscriptionId  = UUID.randomUUID().toString();
        this.userId          = userId;
        this.productId       = productId;
        this.eventTypes      = EnumSet.copyOf(eventTypes);
        this.channelOverrides = channelOverrides.isEmpty()
                ? EnumSet.noneOf(NotificationChannel.class)
                : EnumSet.copyOf(channelOverrides);
        this.subscriptionStatus = SubscriptionStatus.ACTIVE;
        this.subscribedAt    = LocalDateTime.now();
    }

    public boolean covers(EventType type) {
        return subscriptionStatus == SubscriptionStatus.ACTIVE && eventTypes.contains(type);
    }

    // returns the override channels if set, otherwise caller uses user's global preference
    public Set<NotificationChannel> effectiveChannels(User user) {
        return channelOverrides.isEmpty()
                ? user.getPreferredChannels()
                : Set.copyOf(channelOverrides);
    }

    public String getSubscriptionId() { return subscriptionId; }
    public String getUserId()          { return userId; }
    public String getProductId()       { return productId; }
    public SubscriptionStatus getStatus() { return subscriptionStatus; }

    public void pause()  { this.subscriptionStatus = SubscriptionStatus.PAUSED; }
    public void cancel() { this.subscriptionStatus = SubscriptionStatus.CANCELLED; }
    public void resume() { this.subscriptionStatus = SubscriptionStatus.ACTIVE; }
}
