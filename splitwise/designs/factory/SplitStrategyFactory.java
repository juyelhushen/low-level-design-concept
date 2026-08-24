package splitwise.designs.factory;

import splitwise.designs.strategy.EqualSplitStrategy;
import splitwise.designs.strategy.ExactSplitStrategy;
import splitwise.designs.strategy.SplitStrategy;
import splitwise.enums.SplitType;

public class SplitStrategyFactory {
    private SplitStrategyFactory() {
    }

    public static SplitStrategy createSplitStrategy(SplitType type) {
        return switch (type) {
            case EXACT -> ExactSplitStrategy.INSTANCE;
            case EQUAL -> EqualSplitStrategy.INSTANCE;
        };
    }
}
