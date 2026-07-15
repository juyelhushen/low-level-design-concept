package LibraryManagementSystem.factory;

import LibraryManagementSystem.strategy.search.*;

public final class SearchStrategyFactory {
    private SearchStrategyFactory() {
    }

    public static SearchStrategy create(SearchType type) {
        return switch (type) {
            case TITLE -> TitleSearchStrategy.INSTANCE;
            case AUTHOR -> AuthorSearchStrategy.INSTANCE;
            case ISBN -> ISBNSearchStrategy.INSTANCE;
            case CATEGORY -> CategorySearchStrategy.INSTANCE;
        };
    }
}
