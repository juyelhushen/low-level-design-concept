package inventory_update_service.strategy;

import inventory_update_service.entity.Notification;

public interface ChannelStrategy {
    void send(Notification notification);
}
