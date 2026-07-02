package ParkingLot.observer;

import ParkingLot.entity.ParkingFloor;
import ParkingLot.enums.SpotType;

public class DisplayBoard implements ParkingObserver {

    @Override
    public void onSpotStatusChanged(ParkingFloor floor, SpotType type) {
        System.out.println("status changes here");
    }
}
