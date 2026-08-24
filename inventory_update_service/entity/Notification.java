package inventory_update_service.entity;

import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.enums.NotificationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private final String notificationId;
    private final String userId;
    private final String productId;
    private final EventType eventType;
    private final NotificationChannel channel;
    private final String title;
    private final String body;
    private final String actionUrl;
    private final LocalDateTime cretaedAt;
    private NotificationStatus status;

    public Notification(Builder builder) {
        this.notificationId = UUID.randomUUID().toString();
        this.userId = builder.userId;
        this.productId = builder.productId;
        this.eventType = builder.eventType;
        this.channel = builder.channel;
        this.title = builder.title;
        this.body = builder.body;
        this.actionUrl = builder.actionUrl;
        this.cretaedAt = LocalDateTime.now();
        this.status = NotificationStatus.PENDING;
    }

    public void markSent()   { this.status = NotificationStatus.SENT; }
    public void markFailed() { this.status = NotificationStatus.FAILED; }


    public String getNotificationId() {
        return notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public LocalDateTime getCretaedAt() {
        return cretaedAt;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public static class Builder {
        // required
        private final String userId;
        private final String productId;
        private final EventType eventType;
        private final NotificationChannel channel;
        // optional
        private String title  = "";
        private String body   = "";
        private String actionUrl = "";

        public Builder(String userId, String productId,
                       EventType eventType, NotificationChannel channel) {
            this.userId = userId;
            this.productId = productId;
            this.eventType = eventType;
            this.channel = channel;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder actionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }
}
