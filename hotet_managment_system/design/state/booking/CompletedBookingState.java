package hotet_managment_system.design.state.booking;
import hotet_managment_system.entity.Booking;
import hotet_managment_system.enums.BookingStatus;
import hotet_managment_system.exception.InvalidBookingStateException;

public class CompletedBookingState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        throw new InvalidBookingStateException("Booking is already completed.");
    }

    @Override
    public void cancel(Booking booking) {
        throw new InvalidBookingStateException("Booking is already completed.");
    }

    @Override
    public void complete(Booking booking) {
        throw new InvalidBookingStateException("Booking is already completed");
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.COMPLETED;
    }
}
