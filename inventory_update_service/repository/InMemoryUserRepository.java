package inventory_update_service.repository;

import inventory_update_service.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> store = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(store.get(userId));
    }
}
