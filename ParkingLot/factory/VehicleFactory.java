package ParkingLot.factory;

import ParkingLot.entity.Car;
import ParkingLot.entity.MotorBike;
import ParkingLot.entity.Vehicle;
import ParkingLot.enums.VehicleType;

public final class VehicleFactory {
    private VehicleFactory() {
    } // static-only utility class, never instantiated

    // Callers ask for a Vehicle by type and never touch a concrete
    // constructor. Adding a new VehicleType later means adding one
    // switch branch here - every call site elsewhere stays untouched.
    public static Vehicle create(VehicleType type, String licenseNumber) {
        return switch (type) {
            case CAR -> new Car(licenseNumber);
            case MOTORBIKE -> new MotorBike(licenseNumber);
        };
    }
}