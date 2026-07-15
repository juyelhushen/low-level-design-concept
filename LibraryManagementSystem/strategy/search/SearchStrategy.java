package LibraryManagementSystem.strategy.search;

import LibraryManagementSystem.entity.Book;
import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> catalog, String query);
}
