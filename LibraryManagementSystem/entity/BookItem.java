package LibraryManagementSystem.entity;

import LibraryManagementSystem.state.AvailableState;
import LibraryManagementSystem.state.BookItemState;

import java.time.LocalDate;

public class BookItem {

    private final String barcode;       // uniquely identifies this physical copy
    private final Book book;            // the logical catalog entry this copy belongs to
    private final Rack rack;
    private final double price;
    private final LocalDate dateOfPurchase;
    private final boolean isReferenceOnly; // reference-only copies can't be taken home

    private volatile BookItemState currState;

    public BookItem(String barcode, Book book, Rack rack, double price,
                    LocalDate dateOfPurchase, boolean isReferenceOnly) {
        this.barcode = barcode;
        this.book = book;
        this.rack = rack;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.isReferenceOnly = isReferenceOnly;
        this.currState = new AvailableState();
    }

    public void setState(BookItemState state) {
        currState = state;
    }

    public void reserve() {
        currState.reserve(this);
    }

    public void borrow() {
        currState.borrow(this);
    }

    public void returnBook() {
        currState.returnBook(this);
    }

    public void reportLost() {
        currState.reportLost(this);
    }

    public String getBarcode() {
        return barcode;
    }

    public Book getBook() {
        return book;
    }

    public Rack getRack() {
        return rack;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public BookItemState getCurrState() {
        return currState;
    }
}
