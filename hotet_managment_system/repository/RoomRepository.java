package hotet_managment_system.repository;

import hotet_managment_system.entity.Room;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RoomRepository implements Repository<Room> {

    private static Map<String, Room> roomMap = new ConcurrentHashMap<>();

    @Override
    public void save(Room entity) {
        roomMap.put(entity.getRoomId(), entity);
    }

    @Override
    public Optional<Room> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {

    }
}
