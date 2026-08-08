package hotet_managment_system.service;
import java.util.List;

import hotet_managment_system.design.builder.SearchFilter;
import hotet_managment_system.entity.*;
import hotet_managment_system.enums.BookingType;
import hotet_managment_system.enums.PaymentMode;

public interface HotelBookingService {

    User registerUser(String name, String email, String phone);

    void addHotel(Hotel hotel);

    void addRoom(String hotelId, Room room);

    List<Room> searchRoom(SearchFilter filter);

    Booking bookingRoom(String userId, String roomId,
                        DateRange dateRange, PaymentMode mode,
                        BookingType type);

    void cancelBooking(String bookingId);

    List<Booking> getBookingType(String userId);

}
