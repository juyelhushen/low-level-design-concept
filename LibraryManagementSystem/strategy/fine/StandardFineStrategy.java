package LibraryManagementSystem.strategy.fine;

import LibraryManagementSystem.entity.BookLending;

public class StandardFineStrategy implements FineStrategy {

    public static final StandardFineStrategy INSTANCE = new StandardFineStrategy();

    private StandardFineStrategy() {}

    private static final double FINE_PER_DAY = 5.0; // Rs. 5 per overdue day

    @Override
    public double calculateFine(BookLending lending) {
        return lending.getOverdueDays() * FINE_PER_DAY;
    }
}
