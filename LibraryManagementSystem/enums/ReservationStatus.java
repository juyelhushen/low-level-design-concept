package LibraryManagementSystem.enums;

public enum ReservationStatus {
    PENDING,    // waiting for a copy to free up
    READY,      // a copy is available, member notified
    COMPLETED,  // member picked it up
    CANCELLED
}
