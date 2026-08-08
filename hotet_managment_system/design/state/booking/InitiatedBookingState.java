package hotet_managment_system.design.state.booking;

import hotet_managment_system.entity.Booking;
import hotet_managment_system.enums.BookingStatus;
import hotet_managment_system.exception.InvalidBookingStateException;

public class InitiatedBookingState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        booking.setState(new ConfirmedBookingState());
    }

    @Override
    public void cancel(Booking booking) {
        booking.setState(new CancelledBookingState());
    }

    @Override
    public void complete(Booking booking) {
        throw new InvalidBookingStateException(
                "Cannot complete a booking that hasn't been confirmed yet."
        );
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.INITIATED;
    }
}
