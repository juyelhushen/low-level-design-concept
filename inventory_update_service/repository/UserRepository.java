package inventory_update_service.repository;


import inventory_update_service.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String userId);
}