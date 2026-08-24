package inventory_update_service.repository;

import inventory_update_service.entity.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNotificationLogRepository implements NotificationLogRepository {

    private static Map<String, Notification> store = new ConcurrentHashMap<>();
    private static Map<String, List<Notification>> userToNotifications = new ConcurrentHashMap<>();

    @Override
    public void save(Notification notification) {
        // Implementation for saving notification
        store.put(notification.getNotificationId(), notification);
        userToNotifications.computeIfAbsent(notification.getUserId(),
                k-> new ArrayList<>()).add(notification);
    }

    @Override
    public List<Notification> findByUserId(String userId) {
        // Implementation for finding notifications by user ID
        return userToNotifications.getOrDefault(userId, new ArrayList<>());
    }
}
