package LibraryManagementSystem.strategy.search;

import LibraryManagementSystem.entity.Book;

import java.util.List;

public class AuthorSearchStrategy implements SearchStrategy {
    public static final AuthorSearchStrategy INSTANCE = new AuthorSearchStrategy();

    private AuthorSearchStrategy() {
    }

    @Override
    public List<Book> search(List<Book> catalog, String query) {
        String lower = query.toLowerCase();
        return catalog.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(lower))
                .toList();
    }
}
