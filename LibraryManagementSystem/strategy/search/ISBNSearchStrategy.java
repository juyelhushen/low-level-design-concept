package LibraryManagementSystem.strategy.search;

import LibraryManagementSystem.entity.Book;

import java.util.List;

public class ISBNSearchStrategy implements SearchStrategy {

    public static final ISBNSearchStrategy INSTANCE = new ISBNSearchStrategy();

    private ISBNSearchStrategy() {
    }

    @Override
    public List<Book> search(List<Book> catalog, String query) {
        String lower = query.toLowerCase();
        return catalog.stream()
                .filter(b -> b.getIsbn().toLowerCase().contains(lower))
                .toList();
    }
}
