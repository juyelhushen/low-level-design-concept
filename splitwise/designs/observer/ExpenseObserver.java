package splitwise.designs.observer;

import splitwise.entity.Expense;
import splitwise.entity.User;

public interface ExpenseObserver {
    void onExpanseAdded(Expense expense, User payer);
}
