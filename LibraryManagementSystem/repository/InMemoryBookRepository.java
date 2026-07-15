package LibraryManagementSystem.repository;

import LibraryManagementSystem.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBookRepository implements Repository<Book> {

    private final Map<String, Book> byId   = new ConcurrentHashMap<>();
    private final Map<String, Book> byIsbn = new ConcurrentHashMap<>();

    @Override
    public void save(Book book) {
        byId.put(book.getBookId(), book);
        byIsbn.put(book.getIsbn(), book);
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return Optional.ofNullable(byId.get(bookId));
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(byIsbn.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return List.copyOf(byId.values());
    }
}
