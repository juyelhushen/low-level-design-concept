package hotet_managment_system.design.state.booking;

import hotet_managment_system.entity.Booking;
import hotet_managment_system.enums.BookingStatus;
import hotet_managment_system.exception.InvalidBookingStateException;

public class ConfirmedBookingState implements BookingState {


    @Override
    public void confirm(Booking booking) {
        throw new InvalidBookingStateException("Booking is already CONFIRMED.");
    }

    @Override
    public void cancel(Booking booking) {
        booking.setState(new CancelledBookingState());
    }

    @Override
    public void complete(Booking booking) {
        booking.setState(new CompletedBookingState());
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.CONFIRMED;
    }
}
