package ParkingLot.entity;

import ParkingLot.enums.SpotType;

public class MotoBikeSpot extends ParkingSpot {

    public MotoBikeSpot(String spotId, String floorId) {
        super(spotId, floorId, SpotType.MOTORBIKE);
    }
}
