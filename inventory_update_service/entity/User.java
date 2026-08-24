package inventory_update_service.entity;

import inventory_update_service.enums.NotificationChannel;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private final String userId;
    private final String name;
    private final String email;
    private final String phone;
    private final String deviceToken;            // for push notifications

    // user's globally preferred channels — can be overridden per subscription
    private final Set<NotificationChannel> preferredChannels;

    public User(String name, String email,
                String phone, String deviceToken,
                Set<NotificationChannel> preferredChannels) {
        this.userId            = UUID.randomUUID().toString();
        this.name              = name;
        this.email             = email;
        this.phone             = phone;
        this.deviceToken       = deviceToken;
        this.preferredChannels = EnumSet.copyOf(preferredChannels);
    }

    public String getUserId()                          { return userId; }
    public String getName()                            { return name; }
    public String getEmail()                           { return email; }
    public String getPhone()                           { return phone; }
    public String getDeviceToken()                     { return deviceToken; }

    public Set<NotificationChannel> getPreferredChannels() {
        return Set.copyOf(preferredChannels);
    }
}
