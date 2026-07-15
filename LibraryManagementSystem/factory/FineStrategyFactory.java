package LibraryManagementSystem.factory;

import LibraryManagementSystem.enums.MemberType;
import LibraryManagementSystem.strategy.fine.FineStrategy;
import LibraryManagementSystem.strategy.fine.PremiumMemberFineStrategy;
import LibraryManagementSystem.strategy.fine.StandardFineStrategy;

public final class FineStrategyFactory {
    private FineStrategyFactory() {
    }

    public static FineStrategy getStrategy(MemberType memberType) {
        return switch (memberType) {
            case STANDARD -> StandardFineStrategy.INSTANCE;
            case PREMIUM -> PremiumMemberFineStrategy.INSTANCE;
        };
    }
}
