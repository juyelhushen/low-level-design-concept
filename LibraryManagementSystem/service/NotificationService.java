package LibraryManagementSystem.service;

import LibraryManagementSystem.entity.Book;
import LibraryManagementSystem.entity.Member;
import LibraryManagementSystem.observer.LibraryObserver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationService {

    private final List<LibraryObserver> observers = new CopyOnWriteArrayList<>();

    public void registerObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    public void notifyBookAvailable(Book book, Member member) {
        for (LibraryObserver observer : observers) {
            observer.onBookAvailable(book, member);
        }
    }

}
