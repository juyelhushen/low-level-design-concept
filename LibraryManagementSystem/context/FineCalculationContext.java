package LibraryManagementSystem.context;

import LibraryManagementSystem.entity.BookLending;
import LibraryManagementSystem.strategy.fine.FineStrategy;

public class FineCalculationContext {

    private FineStrategy fineStrategy;

    public FineCalculationContext(FineStrategy fineStrategy) {
        this.fineStrategy = fineStrategy;
    }

    public void setFineStrategy(FineStrategy fineStrategy) {
        this.fineStrategy = fineStrategy;
    }

    public double calculateFine(BookLending lending) {
        return fineStrategy.calculateFine(lending);
    }
}
