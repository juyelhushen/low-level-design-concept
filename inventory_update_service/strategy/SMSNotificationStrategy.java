package inventory_update_service.strategy;

import inventory_update_service.entity.Notification;

public class SMSNotificationStrategy implements ChannelStrategy {

    public static final SMSNotificationStrategy INSTANCE = new SMSNotificationStrategy();

    private SMSNotificationStrategy(){}

    @Override
    public void send(Notification notification) {
        // SMS is short — just title + action URL, no long body
        System.out.printf("  [SMS] To: (userId=%s) | %s | %s%n",
                notification.getUserId(), notification.getTitle(),
                notification.getActionUrl());
        notification.markSent();
    }
}
