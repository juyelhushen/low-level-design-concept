package hotet_managment_system.entity;

import hotet_managment_system.design.state.room.AvailableRoomState;
import hotet_managment_system.design.state.room.RoomState;
import hotet_managment_system.enums.AmenityType;
import hotet_managment_system.enums.RoomStatus;
import hotet_managment_system.enums.RoomType;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class Room {

    private final String roomId;
    private final String hotelId;
    private final String roomNumber;
    private final int floor;
    private final RoomType roomType;
    private final double maxPricePerNight;
    private final int maxOccupancy;
    private final Set<AmenityType> amenities;

    //todo state
    private volatile RoomState currentState;

    public Room(
            String hotelId,
            String roomNumber,
            int floor,
            RoomType roomType,
            double maxPricePerNight,
            int maxOccupancy,
            Set<AmenityType> amenities) {
        this.roomId = UUID.randomUUID().toString();
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = roomType;
        this.maxPricePerNight = maxPricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.amenities = EnumSet.copyOf(amenities);
        this.currentState = new AvailableRoomState();
    }

    public void setState(RoomState currentState) {
        this.currentState = currentState;
    }

    public RoomStatus getStatus() {
        return currentState.getStatus();
    }

    public boolean isAvailable() {
        return currentState.getStatus().equals(RoomStatus.AVAILABLE);
    }

    public String getRoomId() {
        return roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Double getMaxPricePerNight() {
        return maxPricePerNight;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public Set<AmenityType> getAmenities() {
        return amenities;
    }
}
