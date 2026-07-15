package LibraryManagementSystem.strategy.fine;

import LibraryManagementSystem.entity.BookLending;

public class PremiumMemberFineStrategy implements FineStrategy {

    public static final PremiumMemberFineStrategy INSTANCE = new PremiumMemberFineStrategy();
    private PremiumMemberFineStrategy() {}

    // premium members get a grace period and lower daily rate
    private static final long GRACE_PERIOD_DAYS = 2;
    private static final double FINE_PER_DAY    = 2.0;

    @Override
    public double calculateFine(BookLending lending) {
        long overdueDays = lending.getOverdueDays();
        long chargeableDays = Math.max(0, overdueDays - GRACE_PERIOD_DAYS);
        return chargeableDays * FINE_PER_DAY;
    }
}
