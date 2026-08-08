package hotet_managment_system.design.observer;

import hotet_managment_system.entity.Booking;

public class SMSNotificationObserver implements BookingObserver {

    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.printf("[SMS] Booking is %s confirmed, check-in: %s%n",
                booking.getBookingId(),
                booking.getDateRange().checkIn());
    }

    @Override
    public void onBookingCancelled(Booking booking) {
        System.out.printf("[SMS] Booking is %s cancelled, refund initiated.%n", booking.getBookingId());
    }
}
