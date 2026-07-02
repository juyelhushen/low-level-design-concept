package ParkingLot.repository;

import ParkingLot.entity.ParkingSpot;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryParkingSpotRepository implements Repository<ParkingSpot> {

    private Map<String, ParkingSpot> inMemoryStore = new ConcurrentHashMap<>();

    @Override
    public void save(ParkingSpot spot) {
        inMemoryStore.put(spot.getSpotId(), spot);
    }

    @Override
    public Optional<ParkingSpot> findById(String id) {
        return Optional.ofNullable(inMemoryStore.get(id));
    }

    @Override
    public List<ParkingSpot> findAll() {
        return List.copyOf(inMemoryStore.values());
    }
}
