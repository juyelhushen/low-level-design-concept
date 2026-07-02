package ParkingLot.observer;

import ParkingLot.entity.ParkingFloor;
import ParkingLot.enums.SpotType;

public interface ParkingObserver {
    void onSpotStatusChanged(ParkingFloor floor, SpotType type);
}
