package inventory_update_service.repository;

import inventory_update_service.entity.Product;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductRepository implements ProductRepository{

    private final Map<String, Product> store = new ConcurrentHashMap<>();

    @Override public void save(Product p) {
        store.put(p.getProductId(), p);
    }

    @Override public Optional<Product> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }


}
