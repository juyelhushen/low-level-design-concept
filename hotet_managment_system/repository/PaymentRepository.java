package hotet_managment_system.repository;

import hotet_managment_system.entity.Payment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository implements Repository<Payment> {

    private static Map<String, Payment> paymentMap = new ConcurrentHashMap<>();

    @Override
    public void save(Payment entity) {
        paymentMap.put(entity.getPaymentId(), entity);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(paymentMap.get(id));
    }

    @Override
    public List<Payment> findAll() {
        return paymentMap.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        paymentMap.remove(id);
    }
}
