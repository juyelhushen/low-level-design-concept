package LibraryManagementSystem.repository;

import LibraryManagementSystem.entity.BookItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBookItemRepository implements Repository<BookItem> {

    private final Map<String, BookItem> store = new ConcurrentHashMap<>();

    @Override
    public void save(BookItem item) {
        store.put(item.getBarcode(), item);
    }

    @Override
    public Optional<BookItem> findById(String barcode) {
        return Optional.ofNullable(store.get(barcode));
    }

    @Override
    public Optional<BookItem> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<BookItem> findAll() {
        return List.of();
    }
}
