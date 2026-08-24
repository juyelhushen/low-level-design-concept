package splitwise.repository;

import splitwise.entity.Balance;

import java.util.List;

public interface BalanceRepository {
    void save(Balance balance);
    // finds existing balance for this pair, or creates a fresh zeroed one
    Balance findOrCreate(String userId1, String userId2);
    List<Balance> findByUserId(String userId);
    List<Balance> findAll();
}