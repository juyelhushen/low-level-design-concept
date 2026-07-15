package LibraryManagementSystem.state;

import LibraryManagementSystem.entity.BookItem;
import LibraryManagementSystem.enums.BookItemStatus;
import LibraryManagementSystem.exception.InvalidStateTransitionException;

public class ReserveState implements BookItemState {

    @Override
    public void reserve(BookItem item) {
        throw new InvalidStateTransitionException(
                "Cannot borrow a BookItem that is already Reserved.");
    }

    @Override
    public void borrow(BookItem item) {
        item.setState(new BorrowState());
    }

    @Override
    public void returnBook(BookItem item) {
        // legal: member cancels their reservation — copy goes back to available
        item.setState(new AvailableState());
    }

    @Override
    public void reportLost(BookItem item) {
        // edge case: reserved copy goes missing before pickup
        item.setState(new LostState());
    }

    @Override
    public BookItemStatus getStatus() {
        return BookItemStatus.RESERVED;
    }
}
