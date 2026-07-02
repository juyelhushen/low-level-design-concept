package ParkingLot.entity;

import ParkingLot.enums.SpotType;
import ParkingLot.enums.VehicleType;

import java.util.List;

public class Car extends Vehicle{

    public Car(String licensePlate) {
        super(licensePlate,VehicleType.CAR);
    }

    @Override
    public List<SpotType> getCompatibleSpot() {
        return List.of(SpotType.COMPACT);
    }
}
