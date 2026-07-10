package LibraryManagementSystem.entity;

import LibraryManagementSystem.enums.BookCategory;
import LibraryManagementSystem.enums.BookFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Book {
    private final String bookId;
    private final String isbn;
    private final String title;
    private final String author;
    private final String publisher;
    private final BookCategory bookCategory;
    private final BookFormat format;

    private final List<BookItem> copies = new ArrayList<>();
    private final Queue<String> reservationQueue = new ConcurrentLinkedQueue<>();

    public Book(String bookId, String isbn, String title,
                String author, String publisher,
                BookCategory bookCategory, BookFormat format) {

        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookCategory = bookCategory;
        this.format = format;
    }

    public String getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public BookFormat getFormat() {
        return format;
    }

    public List<BookItem> getCopies() {
        return Collections.unmodifiableList(copies);
    }

    public Queue<String> getReservationQueue() {
        return reservationQueue;
    }

    public void enqueueReservation(String memberId) {
        reservationQueue.add(memberId);
    }

    public String dequeueNextReservation() {
        return reservationQueue.poll();
    }

    public boolean hasReservationQueue() {
        return !reservationQueue.isEmpty();
    }
}
