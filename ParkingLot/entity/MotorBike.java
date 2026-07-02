package ParkingLot.entity;

import ParkingLot.enums.SpotType;
import ParkingLot.enums.VehicleType;

import java.util.List;

public class MotorBike extends Vehicle {

    public MotorBike(String licensePlate) {
        super(licensePlate, VehicleType.MOTORBIKE);
    }

    @Override
    public List<SpotType> getCompatibleSpot() {
        return List.of(SpotType.MOTORBIKE,SpotType.COMPACT);
    }
}
