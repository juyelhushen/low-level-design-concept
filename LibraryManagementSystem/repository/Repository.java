package LibraryManagementSystem.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T t);
    Optional<T> findById(String bookId);
    Optional<T> findByIsbn(String isbn);
    List<T> findAll();
}
