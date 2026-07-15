package LibraryManagementSystem.state;

import LibraryManagementSystem.entity.BookItem;
import LibraryManagementSystem.enums.BookItemStatus;
import LibraryManagementSystem.exception.InvalidStateTransitionException;

public class LostState implements BookItemState {

    @Override
    public void reserve(BookItem item) {
        throw new InvalidStateTransitionException(
                "Cannot reserve a BookItem that is already BORROWED. " +
                        "Join the reservation queue on the Book instead.");
    }

    @Override
    public void borrow(BookItem item) {
        throw new InvalidStateTransitionException(
                "Cannot borrow a BookItem that is already BORROWED.");

    }

    @Override
    public void returnBook(BookItem item) {
        // the normal happy path — member returns the copy
        item.setState(new AvailableState());
    }

    @Override
    public void reportLost(BookItem item) {
        // member reports loss while the book is with them
        throw new InvalidStateTransitionException("Book item is already marked LOST.");
    }

    // recovery path — only accessible via admin/librarian action
    public void markFound(BookItem item) {
        item.setState(new AvailableState());
    }

    @Override
    public BookItemStatus getStatus() {
        return BookItemStatus.LOST;
    }
}
