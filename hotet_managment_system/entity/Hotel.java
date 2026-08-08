package hotet_managment_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hotel {
    private final String hotelId;
    private final String name;
    private final Address address;
    private final String description;
    private double starRating;
    private final List<Room> rooms = new ArrayList<>();

    public Hotel(String name, Address address, String description, double starRating) {
        this.hotelId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.description = description;
        this.starRating = starRating;
    }

    public void addRooms(Room room) {
        this.rooms.add(room);
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public double getStarRating() {
        return starRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
