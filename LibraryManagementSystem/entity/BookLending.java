package LibraryManagementSystem.entity;

import LibraryManagementSystem.enums.LendingStatus;

import java.time.LocalDate;

public class BookLending {

    private final String lendingId;
    private final String barcode;
    private final String memberId;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private LendingStatus status;

    public static final int STANDARD_LOAN_DAYS = 14;
    public static final int PREMIUM_LOAN_DAYS  = 21;

    public BookLending(String lendingId, String barcode,
                       String memberId, LocalDate dueDate,
                       LendingStatus status) {
        this.lendingId = lendingId;
        this.barcode = barcode;
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.status = LendingStatus.ACTIVE;
    }

    public void markDueDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = returnDate.isAfter(dueDate) ?
                LendingStatus.OVERDUE : LendingStatus.RETURNED;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && status == LendingStatus.ACTIVE;
    }

    public long getOverdueDays() {
        if (!isOverdue()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

}
