package LibraryManagementSystem.factory;

import LibraryManagementSystem.entity.Book;
import LibraryManagementSystem.entity.BookItem;
import LibraryManagementSystem.entity.Rack;

import java.time.LocalDate;

public final class BookItemFactory {

    private BookItemFactory() {
    }

    public static BookItem create(String barcode, Book book, Rack rack, double price,
                                  LocalDate dateOfPurchase, boolean isReferenceOnly) {
        return new BookItem(barcode, book, rack, price, LocalDate.now(), isReferenceOnly);
    }
}
