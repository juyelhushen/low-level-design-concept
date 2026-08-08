package hotet_managment_system.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import hotet_managment_system.entity.User;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements Repository<User> {

    private static Map<String, User> userMap = new ConcurrentHashMap<>();
    private static Map<String, User> eamilUserMap = new ConcurrentHashMap<>();

    @Override
    public void save(User entity) {
        userMap.put(entity.getUserId(), entity);
        eamilUserMap.put(entity.getEmail(), entity);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> findAll() {
        return userMap.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    Optional<User> findByEmail(String email) {
        return Optional.ofNullable(eamilUserMap.get(email));
    }
}
