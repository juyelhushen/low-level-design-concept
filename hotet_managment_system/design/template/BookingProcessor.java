package hotet_managment_system.design.template;

import hotet_managment_system.entity.*;
import hotet_managment_system.enums.PaymentMode;
import hotet_managment_system.enums.PaymentStatus;
import hotet_managment_system.enums.UserStatus;
import hotet_managment_system.exception.PaymentFailedException;
import hotet_managment_system.exception.RoomNotAvailableException;
import hotet_managment_system.exception.RoomNotFoundException;
import hotet_managment_system.exception.UserNotFoundException;
import hotet_managment_system.repository.BookingRepository;
import hotet_managment_system.repository.PaymentRepository;
import hotet_managment_system.repository.RoomRepository;
import hotet_managment_system.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BookingProcessor {

    protected final UserRepository userRepo;
    protected final RoomRepository roomRepo;
    protected final BookingRepository bookingRepo;
    protected final PaymentRepository paymentRepo;

    public BookingProcessor(
            UserRepository userRepo,
            RoomRepository roomRepo,
            BookingRepository bookingRepo,
            PaymentRepository paymentRepo) {
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;
    }

    /*
     * THE template method — final so no subclass can reorder these steps.
     * Steps 3 and 5 are abstract and must be provided by subclasses.
     * Steps 1, 2, 4, 6, 7 are common to all booking types.
     */
    public final Booking process(String userId, String roomId,
                                 DateRange dateRange, PaymentMode mode) {
        User user = validateUser(userId);           // step 1: common
        Room room = checkAvailability(roomId, dateRange); // step 2: common
        double price = calculateTotalPrice(room, dateRange); // step 3: ABSTRACT
        Booking booking = initiateBooking(user, room, dateRange, price); // step 4: common
        Payment payment = processPayment(booking, mode); // step 5: ABSTRACT
        confirmBooking(booking, payment);            // step 6: common
        postProcess(booking);                        // step 7: hook (optional override)
        return booking;
    }

    // ---- Common steps — shared implementation ----

    protected User validateUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        if (user.getUserStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("User account is not active.");
        }
        return user;
    }

    protected Room checkAvailability(String roomId, DateRange dateRange) {
        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found: " + roomId));
        if (!room.isAvailable()) {
            throw new RoomNotAvailableException("Room " + roomId + " is under maintenance.");
        }
        // check for date overlaps against existing confirmed bookings
        boolean overlapExists = bookingRepo.findConfirmedByRoomId(roomId).stream()
                .anyMatch(b -> b.getDateRange().overlaps(dateRange));
        if (overlapExists) {
            throw new RoomNotAvailableException(
                    "Room " + roomId + " is already booked for overlapping dates.");
        }
        return room;
    }

    protected Booking initiateBooking(User user, Room room,
                                      DateRange dateRange, double price) {
        Booking booking = new Booking(user.getUserId(), room.getRoomId(),
                room.getHotelId(), dateRange, price, LocalDateTime.now());
        bookingRepo.save(booking);
        user.addBookingsId(booking.getBookingId());
        return booking;
    }

    protected void confirmBooking(Booking booking, Payment payment) {
        if (payment.getPaymentStatus() == PaymentStatus.COMPLETED) {
            booking.setPaymentId(payment.getPaymentId());
            booking.confirm(); // State machine: INITIATED → CONFIRMED
            bookingRepo.save(booking);
            System.out.printf("[Booking] Confirmed %s | Rs.%.2f | %s → %s%n",
                    booking.getBookingId(), booking.getTotalPrice(),
                    booking.getDateRange().checkIn(), booking.getDateRange().checkOut());
        } else {
            booking.cancel(); // State machine: INITIATED → CANCELLED
            bookingRepo.save(booking);
            throw new PaymentFailedException("Payment failed. Booking cancelled.");
        }
    }

    // Hook: subclasses can override for post-processing (sending receipts etc.)
    // Default is a no-op — not abstract, so subclasses don't have to implement it.
    protected abstract void postProcess(Booking booking);

    // ---- Abstract steps — subclasses define these ----

    protected abstract double calculateTotalPrice(Room room, DateRange dateRange);

    protected abstract Payment processPayment(Booking booking, PaymentMode mode);


}
