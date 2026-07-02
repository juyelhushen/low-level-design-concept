package ParkingLot.entity;

import ParkingLot.enums.SpotStatus;
import ParkingLot.enums.SpotType;
import java.util.concurrent.atomic.AtomicReference;


abstract public class ParkingSpot {

    private final String spotId;
    private final String floorId;
    private final SpotType spotType;
    private final AtomicReference<SpotStatus> status;
    private volatile Vehicle parkedVehicle;

    public ParkingSpot(String spotId, String floorId, SpotType spotType) {
        this.spotId = spotId;
        this.floorId = floorId;
        this.spotType = spotType;
        this.status = new AtomicReference<>(SpotStatus.FREE);
    }

    public String getSpotId() {
        return spotId;
    }

    public String getFloorId() {
        return floorId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public AtomicReference<SpotStatus> getStatus() {
        return status;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    //tryOccupy
    public boolean tryOccupy(Vehicle vehicle) {
        boolean claimed = status.compareAndSet(SpotStatus.FREE, SpotStatus.BOOKED);
        if (claimed) {
            this.parkedVehicle = vehicle;
        }
        return claimed;
    }


    //release
    public void release() {
        this.parkedVehicle = null;
        status.set(SpotStatus.FREE);
    }
}
