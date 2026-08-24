package inventory_update_service.strategy;

import inventory_update_service.entity.Notification;

public class EmailNotificationStrategy implements ChannelStrategy {

    public static final EmailNotificationStrategy INSTANCE = new EmailNotificationStrategy();
    private EmailNotificationStrategy() {}

    @Override
    public void send(Notification notification) {
        System.out.printf("  [EMAIL] To: (userId=%s) | Subject: %s | %s | %s%n",
                notification.getUserId(), notification.getTitle(),
                notification.getBody(), notification.getActionUrl());
        notification.markSent();
    }
}
