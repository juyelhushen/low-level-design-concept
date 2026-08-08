package hotet_managment_system.repository;

import hotet_managment_system.entity.Hotel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class HotelRepository implements Repository<Hotel> {

    private final Map<String, Hotel> hotels = new ConcurrentHashMap<>();

    @Override
    public void save(Hotel entity) {
        hotels.put(entity.getHotelId(), entity);
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Hotel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {

    }

    public List<Hotel> findByCity(String city) {
        return hotels.values().stream()
                .filter(hotel -> city.equals(hotel.getAddress().city()))
                .toList();
    }


}
