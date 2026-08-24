package inventory_update_service.service;

import inventory_update_service.entity.Notification;
import inventory_update_service.entity.User;
import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.factory.ChannelStrategyFactory;
import inventory_update_service.repository.NotificationLogRepository;
import inventory_update_service.strategy.ChannelStrategy;

public class NotificationService {
    private final NotificationLogRepository notificationLogRepository;

    public NotificationService(NotificationLogRepository notificationLogRepository) {
        this.notificationLogRepository = notificationLogRepository;
    }

    public void dispatch(User user, String productId, EventType type,
                         NotificationChannel channel, String title, String body, String actionUrl) {

        Notification notification = new Notification.Builder(
                user.getUserId(),
                productId,
                type,
                channel
        )
                .actionUrl(actionUrl)
                .body(body)
                .title(title)
                .build();

        ChannelStrategy strategy = ChannelStrategyFactory.getStrategy(channel);
        strategy.send(notification);
        notificationLogRepository.save(notification);
    }
}
