package hotet_managment_system.design.observer;

import hotet_managment_system.entity.Booking;

public class EmailNotificationObserver implements BookingObserver {

    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.printf("[Email] Booking %s confirmed, check-in : %s%n" +
                booking.getBookingId(), booking.getDateRange().checkIn());
    }

    @Override
    public void onBookingCancelled(Booking booking) {
        System.out.printf("[Email] Booking %s cancelled%n, Refund initiated.%n", booking.getBookingId());
    }
}