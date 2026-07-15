package LibraryManagementSystem.state;

import LibraryManagementSystem.entity.BookItem;
import LibraryManagementSystem.enums.BookItemStatus;
import LibraryManagementSystem.exception.InvalidStateTransitionException;

public class BorrowState implements BookItemState {

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
        item.setState(new AvailableState());
    }

    @Override
    public void reportLost(BookItem item) {
        item.setState(new LostState());
    }

    @Override
    public BookItemStatus getStatus() {
        return BookItemStatus.BORROWED;
    }
}
