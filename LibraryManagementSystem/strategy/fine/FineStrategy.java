package LibraryManagementSystem.strategy.fine;

import LibraryManagementSystem.entity.BookLending;

public interface FineStrategy {
    double calculateFine(BookLending lending);
}
