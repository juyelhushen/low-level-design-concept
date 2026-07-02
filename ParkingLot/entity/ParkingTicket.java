package ParkingLot.entity;

import ParkingLot.enums.TicketStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final String floorId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatus status;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.floorId = spot.getFloorId();
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public String getFloorId() {
        return floorId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void close() {
        this.exitTime = LocalDateTime.now();
        this.status = TicketStatus.CLOSED;
    }

    public Duration getParkedDuration() {
        LocalDateTime end = (exitTime != null) ? exitTime : LocalDateTime.now();
        return Duration.between(this.entryTime, end);
    }
}
