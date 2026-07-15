package LibraryManagementSystem.observer;

import LibraryManagementSystem.entity.Book;
import LibraryManagementSystem.entity.Member;

// Observer pattern — anything that wants to react to a book becoming
// available implements this. NotificationService registers one or more
// implementations (email, SMS) without Book or Library knowing they exist.
public interface LibraryObserver {
    void onBookAvailable(Book book, Member member);
}