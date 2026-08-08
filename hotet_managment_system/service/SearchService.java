package hotet_managment_system.service;

import hotet_managment_system.design.builder.SearchFilter;
import hotet_managment_system.entity.Room;
import hotet_managment_system.repository.BookingRepository;
import hotet_managment_system.repository.HotelRepository;

import java.util.List;

public class SearchService {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    public SearchService(HotelRepository hotelRepository, BookingRepository bookingRepository) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Room> searchRoom(SearchFilter searchFilter) {
        return hotelRepository.findByCity(searchFilter.getLocation())
                .stream()
                .flatMap(hotel -> hotel.getRooms().stream())
                .filter(Room::isAvailable)
                .filter(r -> r.getMaxOccupancy() >= searchFilter.getGuests())
                .filter(r -> bookingRepository.findConfirmedByRoomId(r.getRoomId())
                        .stream().noneMatch(b -> b.getDateRange().overlaps(searchFilter.getDateRange())))
                .filter(r -> searchFilter.getRoomType() == null
                        || r.getRoomType() == searchFilter.getRoomType())
                .filter(r -> r.getMaxPricePerNight() == null
                        || r.getMaxPricePerNight() <= searchFilter.getMaxPricePerNight())
                .filter(r -> r.getAmenities().containsAll(searchFilter.getRequiredAmenities()))
                .toList();
    }
}
