package hotet_managment_system.entity;

import hotet_managment_system.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final String userId;
    private final String name;
    private final String email;
    private final String phone;
    private UserStatus userStatus;
    private final LocalDateTime registeredAt;

    private final List<String> bookingsId = new ArrayList<>();

    public User(String name, String email, String phone) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registeredAt = LocalDateTime.now();
        this.userStatus = UserStatus.ACTIVE;
    }

    public void addBookingsId(String bookingId) {
        this.bookingsId.add(bookingId);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public List<String> getBookingsId() {
        return bookingsId;
    }
}


