package hotet_managment_system.repository;

import hotet_managment_system.entity.Booking;

import java.util.List;
import java.util.Optional;


public class BookingRepository implements Repository<Booking> {

    @Override
    public void save(Booking entity) {

    }

    @Override
    public Optional<Booking> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Booking> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {

    }

    public List<Booking> findByUserId(String userId) {
        return List.of();
    }
    // used by availability check — only CONFIRMED bookings block dates
    public List<Booking> findConfirmedByRoomId(String roomId) {
        return List.of();
    }
}
