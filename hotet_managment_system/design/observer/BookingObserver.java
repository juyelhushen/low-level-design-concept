package hotet_managment_system.design.observer;

import hotet_managment_system.entity.Booking;

public interface BookingObserver {
    void onBookingConfirmed(Booking booking);
    void onBookingCancelled(Booking booking);
}


