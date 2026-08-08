package hotet_managment_system.service;

import hotet_managment_system.design.builder.SearchFilter;
import hotet_managment_system.design.observer.BookingObserver;
import hotet_managment_system.design.strategy.pricing.PricingContext;
import hotet_managment_system.design.strategy.pricing.StandardPricingStrategy;
import hotet_managment_system.design.template.BookingProcessor;
import hotet_managment_system.design.template.InstantBookingProcessor;
import hotet_managment_system.design.template.StandardBookingProcessor;
import hotet_managment_system.entity.*;
import hotet_managment_system.enums.BookingStatus;
import hotet_managment_system.enums.BookingType;
import hotet_managment_system.enums.PaymentMode;
import hotet_managment_system.exception.InvalidBookingStateException;
import hotet_managment_system.repository.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HotelBookingServiceImpl implements HotelBookingService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final SearchService searchService;
    private final List<BookingObserver> observers = new CopyOnWriteArrayList<>();

    public HotelBookingServiceImpl(HotelRepository hotelRepository, UserRepository userRepository,
                                   RoomRepository roomRepository, BookingRepository bookingRepository,
                                   PaymentRepository paymentRepository, SearchService searchService) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.searchService = searchService;
    }

    public void registerObserver(BookingObserver observer) {
        observers.add(observer);
    }

    @Override
    public User registerUser(String name, String email, String phone) {
        User user = new User(name, email, phone);
        userRepository.save(user);
        System.out.println("[User] Registered: " + name);
        return user;
    }

    @Override
    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void addRoom(String hotelId, Room room) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        hotel.addRooms(room);
        roomRepository.save(room);
    }

    @Override
    public List<Room> searchRoom(SearchFilter filter) {
        return searchService.searchRoom(filter);
    }

    @Override
    public Booking bookingRoom(String userId, String roomId, DateRange dateRange,
                               PaymentMode mode, BookingType type) {
        // Template Method: factory picks which processor (Standard or Instant);
        // the 7-step skeleton runs identically for both; only steps 3 and 5 differ.
        PricingContext pricingContext = new PricingContext(StandardPricingStrategy.INSTANCE);
        BookingProcessor processor = switch (type) {
            case STANDARD -> new StandardBookingProcessor(userRepository, roomRepository,
                    bookingRepository, paymentRepository,pricingContext);
            case INSTANT -> new InstantBookingProcessor(
                    userRepository, roomRepository, bookingRepository,paymentRepository, pricingContext
            );
        };

        Booking booking = processor.process(userId, roomId, dateRange, mode);

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            observers.forEach(o -> o.onBookingConfirmed(booking));
        }

        return booking;
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingStateException(
                        "Booking not found: " + bookingId));
        booking.cancel(); // State machine handles legality check
        bookingRepository.save(booking);
        observers.forEach(o -> o.onBookingCancelled(booking));
    }

    @Override
    public List<Booking> getBookingType(String userId) {
        return bookingRepository.findByUserId(userId);
    }

}
