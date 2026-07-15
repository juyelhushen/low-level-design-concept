package LibraryManagementSystem.strategy.search;

import LibraryManagementSystem.entity.Book;

import java.util.List;

public class TitleSearchStrategy implements SearchStrategy {

    public static final TitleSearchStrategy INSTANCE = new TitleSearchStrategy();

    private TitleSearchStrategy() {
    }

    @Override
    public List<Book> search(List<Book> catalog, String query) {
        String lower = query.toLowerCase();
        return catalog.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(lower))
                .toList();
    }
}
