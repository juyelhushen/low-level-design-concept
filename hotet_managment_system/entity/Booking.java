package hotet_managment_system.entity;

import hotet_managment_system.design.state.booking.BookingState;
import hotet_managment_system.design.state.booking.InitiatedBookingState;
import hotet_managment_system.enums.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private final String bookingId;
    private final String userId;
    private final String roomId;
    private final String hotelId;
    private final DateRange dateRange;
    private final double totalPrice;
    private final LocalDateTime createdAt;
    private String paymentId;

    //todo state
    public volatile BookingState currentState;

    public Booking(
            String userId,
            String roomId,
            String hotelId,
            DateRange dateRange,
            double totalPrice,
            LocalDateTime createdAt) {
        this.bookingId = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.dateRange = dateRange;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.currentState = new InitiatedBookingState();
    }

    public void confirm() {
        currentState.confirm(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void complete() {
        currentState.complete(this);
    }

    public void setState(BookingState state){
        this.currentState = state;
    }

    public BookingStatus getStatus() {
        return currentState.getStatus();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
