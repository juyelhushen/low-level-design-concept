package ParkingLot.factory;

import ParkingLot.entity.CarSpot;
import ParkingLot.entity.MotoBikeSpot;
import ParkingLot.entity.ParkingSpot;
import ParkingLot.enums.SpotType;

public final class ParkingSpotFactory {
    private static final int DEFAULT_CHARGING_POWER_KW = 7;

    private ParkingSpotFactory() {
    }

    public static ParkingSpot create(SpotType type, String spotId, String floorId) {
        return switch (type) {
            case COMPACT -> new CarSpot(spotId, floorId);
            case MOTORBIKE -> new MotoBikeSpot(spotId, floorId);
        };
    }
}