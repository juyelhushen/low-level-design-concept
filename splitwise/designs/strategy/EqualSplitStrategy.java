package splitwise.designs.strategy;

import splitwise.entity.Split;
import java.util.List;

public final class EqualSplitStrategy implements SplitStrategy {

    public static final EqualSplitStrategy INSTANCE = new EqualSplitStrategy();

    private EqualSplitStrategy() {}

    @Override
    public void validate(List<Split> splits, long totalAmount) {
        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits list cannot be empty or null");
        }
        if (totalAmount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
    }

    @Override
    public void computeSplits(List<Split> splits, long totalAmount) {
        int n = splits.size();

        long base = totalAmount / n;
        long remainder = totalAmount % n;

        // Distribute remainder paise one unit at a time to the first participants.
        // e.g. ₹100 (10000 paise) / 3 people:
        //   base=3333, remainder=1
        //   person[0] → 3334 paise (₹33.34)
        //   person[1] → 3333 paise (₹33.33)
        //   person[2] → 3333 paise (₹33.33)
        //   sum = 10000 paise ✓  — no floating point drift possible

        for (int i = 0; i < n; i++) {
            splits.get(i).setAmount(base + (i < remainder ? 1 : 0));
        }
    }
}
