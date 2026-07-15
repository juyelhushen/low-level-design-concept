package LibraryManagementSystem.entity;

import LibraryManagementSystem.enums.MemberStatus;
import LibraryManagementSystem.enums.MemberType;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public final class Member extends Person {
    private final MemberType memberType;
    private MemberStatus memberStatus;
    private final LocalDate membershipExpiryDate;

    private final AtomicInteger totalBooksCheckedOut = new AtomicInteger(0);

    private static final int MAX_BOOKS_STANDARD = 5;
    private static final int MAX_BOOKS_PREMIUM = 10;

    public Member(String personId,
                  String name, String phone,
                  MemberType memberType,
                  LocalDate membershipExpiryDate) {
        super(personId, name, phone);
        this.memberType = memberType;
        this.membershipExpiryDate = membershipExpiryDate;
        this.memberStatus = MemberStatus.ACTIVE;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public LocalDate getMembershipExpiryDate() {
        return membershipExpiryDate;
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut.get();
    }

    public int getMaxBooksAllowed() {
        return switch (memberType) {
            case STANDARD -> MAX_BOOKS_STANDARD;
            case PREMIUM -> MAX_BOOKS_PREMIUM;
        };
    }

    public boolean incrementBorrowCount() {
        int current;
        do {
            current = totalBooksCheckedOut.get();
            if (current >= getMaxBooksAllowed()) return false;
        } while (!totalBooksCheckedOut.compareAndSet(current, current + 1));

        return true;
    }

    public void decrementBorrowCount() {
        totalBooksCheckedOut.decrementAndGet();
    }

    public void suspend() {
        this.memberStatus = MemberStatus.SUSPENDED;
    }

    public void activate() {
        this.memberStatus = MemberStatus.ACTIVE;
    }
}
