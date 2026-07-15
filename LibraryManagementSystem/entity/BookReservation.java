package LibraryManagementSystem.entity;

import LibraryManagementSystem.enums.ReservationStatus;

import java.time.LocalDate;

public class BookReservation {

    private final String reservationId;
    private final String bookId;     // reserved against the Book (catalog), not a specific copy
    private final String memberId;
    private final LocalDate reservationDate;
    private ReservationStatus status;

    public BookReservation(String reservationId, String bookId,
                           String memberId, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
        this.status = ReservationStatus.PENDING;
    }

    public String getReservationId()    { return reservationId; }
    public String getBookId()           { return bookId; }
    public String getMemberId()         { return memberId; }
    public LocalDate getReservationDate() { return reservationDate; }
    public ReservationStatus getStatus(){ return status; }

    public void markReady()     { this.status = ReservationStatus.READY; }
    public void markCompleted() { this.status = ReservationStatus.COMPLETED; }
    public void cancel()        { this.status = ReservationStatus.CANCELLED; }
}
