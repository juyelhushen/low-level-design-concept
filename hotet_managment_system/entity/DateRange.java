package hotet_managment_system.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public record DateRange(LocalDate checkIn,
                        LocalDate checkOut
) {

    // Compact constructor: Java 21 lets you validate invariants here
    // without repeating field assignments. This runs before the record
    // stores anything — an invalid DateRange can never be constructed.
    public DateRange {
        Objects.requireNonNull(checkIn,  "checkIn cannot be null");
        Objects.requireNonNull(checkOut, "checkOut cannot be null");
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException(
                    "checkOut must be strictly after checkIn. Got: " + checkIn + " → " + checkOut);
        }
    }

    public long nights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }


    /**
     * True if this range overlaps with another.
     *
     * Boundary rule: a guest checking out on day D and another checking
     * in on day D is NOT an overlap — the room is cleaned between them.
     * So the condition is strictly: checkIn < other.checkOut AND checkOut > other.checkIn
     * (not <= / >=). This is the edge case that causes double-booking bugs
     * when people use >= by accident.
     */
    public boolean overlaps(DateRange other) {
        return this.checkIn.isBefore(other.checkOut)
                && this.checkOut.isAfter(other.checkIn);
    }


}
