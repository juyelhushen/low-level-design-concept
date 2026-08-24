package splitwise.designs.strategy;

import splitwise.entity.Split;

import java.util.List;

public class SplitStrategyContext {

    private SplitStrategy splitStrategy;

    private SplitStrategyContext() {}

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public void calculateSplits(List<Split> splits, long totalAmount) {
        splitStrategy.validate(splits, totalAmount);
        splitStrategy.computeSplits(splits, totalAmount);
    }
}
