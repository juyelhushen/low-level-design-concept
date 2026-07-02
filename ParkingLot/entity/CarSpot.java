package ParkingLot.entity;

import ParkingLot.enums.SpotType;

public class CarSpot extends ParkingSpot {

    public CarSpot(String spotId, String floorId) {
        super(spotId, floorId, SpotType.COMPACT);
    }
}
