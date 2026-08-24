package inventory_update_service.repository;

import inventory_update_service.entity.Notification;

import java.util.List;

public interface NotificationLogRepository {
    void save(Notification notification);
    List<Notification> findByUserId(String userId);
}
