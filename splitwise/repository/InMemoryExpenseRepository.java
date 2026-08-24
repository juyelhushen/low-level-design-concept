package splitwise.repository;

import splitwise.entity.Expense;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryExpenseRepository implements  ExpenseRepository {

    private final Map<String, Expense> store = new ConcurrentHashMap<>();

    @Override public void save(Expense e)                       { store.put(e.getExpenseId(), e); }
    @Override public Optional<Expense> findById(String id)     { return Optional.ofNullable(store.get(id)); }
    @Override public List<Expense> findByGroupId(String gid)   {
        return store.values().stream().filter(e -> gid.equals(e.getGroupId())).toList();
    }
    @Override public List<Expense> findByPayerId(String pid)   {
        return store.values().stream().filter(e -> pid.equals(e.getPayerId())).toList();
    }
}
