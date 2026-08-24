package splitwise.repository;

import splitwise.entity.Balance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBalanceRepository implements BalanceRepository{

    // key = "minUserId|maxUserId" — enforced by Balance.getKey()
    private final Map<String, Balance> store = new ConcurrentHashMap<>();

    @Override
    public void save(Balance balance) {
        store.put(balance.debtorId(), balance);
    }

    @Override
    public Balance findOrCreate(String userId1, String userId2) {
        // Balance constructor enforces canonical ordering internally
        Balance probe = new Balance(userId1,userId2, 0);
        return store.computeIfAbsent(probe.debtorId(), k -> probe);
    }

    @Override
    public List<Balance> findByUserId(String userId) {
        return store.values().stream()
                .filter(b -> b.debtorId().equals(userId)
                        || b.creditorId().equals(userId))
                .toList();
    }

    @Override
    public List<Balance> findAll() {
        return List.copyOf(store.values());
    }
}
