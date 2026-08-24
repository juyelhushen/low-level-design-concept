package splitwise.entity;

import splitwise.enums.ExpenseCategory;
import splitwise.enums.SplitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Expense {

    private final String expenseId;
    private final String description;
    private final long amount;
    private final String payerId;
    private final String groupId;            /// can be null if between friends
    private final SplitType type;
    private final ExpenseCategory category;
    private final List<Split> splits;
    private final LocalDateTime createdAt;

    public Expense(Builder builder) {
        this.expenseId = java.util.UUID.randomUUID().toString();
        this.description = builder.description;
        this.amount = builder.amount;
        this.payerId = builder.payerId;
        this.groupId = builder.groupId;
        this.type = builder.type;
        this.category = builder.category;
        this.splits = List.copyOf(builder.splits);
        this.createdAt = LocalDateTime.now();
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public long getAmount() {
        return amount;
    }

    public String getPayerId() {
        return payerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public SplitType getType() {
        return type;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public static class Builder {
        // required
        private final String expenseId;
        private final String description;
        private final long amount;
        private final String payerId;
        private final SplitType type;

        // optional
        private String groupId;
        private ExpenseCategory category = ExpenseCategory.GENERAL;
        private final List<Split> splits = new ArrayList<>();

        public Builder(String expenseId, String description, long amount,
                       String payerId, SplitType splitType) {
            this.expenseId = expenseId;
            this.description = description;
            this.amount = amount;
            this.payerId = payerId;
            this.type = splitType;
        }

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withCategory(ExpenseCategory category) {
            this.category = category;
            return this;
        }

        public Builder addSplit(Split split) {
            this.splits.add(split);
            return this;
        }

        public Expense build() {
            return new Expense(this);
        }
    }
}
