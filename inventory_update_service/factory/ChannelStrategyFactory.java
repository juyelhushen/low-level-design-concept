package inventory_update_service.factory;

import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.strategy.*;

public class ChannelStrategyFactory {
    private ChannelStrategyFactory(){}

    // Exhaustive switch — no default. Adding a new NotificationChannel
    // enum constant without a branch here is a compile error.
    public static ChannelStrategy getStrategy(NotificationChannel channel) {
        return switch (channel) {
            case EMAIL ->  EmailNotificationStrategy.INSTANCE;
            case SMS -> SMSNotificationStrategy.INSTANCE;
            case PUSH -> PushNotificationStrategy.INSTANCE;
            case IN_APP -> INAPPNotificationStrategy.INSTANCE;
        };
    }
}
