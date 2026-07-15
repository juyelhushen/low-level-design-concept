package LibraryManagementSystem.strategy.search;

import LibraryManagementSystem.entity.Book;
import LibraryManagementSystem.enums.BookCategory;

import java.util.List;

public class CategorySearchStrategy implements SearchStrategy{

    public static final CategorySearchStrategy INSTANCE = new CategorySearchStrategy();

    private CategorySearchStrategy() {
    }

    @Override
    public List<Book> search(List<Book> catalog, String query) {
       try {
           BookCategory target =  BookCategory.valueOf(query);
           return catalog.stream()
                   .filter(b -> b.getBookCategory() == target)
                   .toList();
       } catch (IllegalArgumentException e) {
           return List.of();
       }
    }
}
