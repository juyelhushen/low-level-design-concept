package inventory_update_service.strategy;

import inventory_update_service.entity.Notification;

public class INAPPNotificationStrategy implements ChannelStrategy {

    public static final INAPPNotificationStrategy INSTANCE = new INAPPNotificationStrategy();

    private INAPPNotificationStrategy(){}

    @Override
    public void send(Notification notification) {
        // In-app: stored in notification bell, shown on next app open
        System.out.printf("  [IN-APP] Queued for (userId=%s) | %s%n",
                notification.getUserId(), notification.getTitle());
        notification.markSent();
    }
}
