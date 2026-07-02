package ParkingLot.factory;

import ParkingLot.enums.VehicleType;
import ParkingLot.strategy.pricing.CarPricingStrategy;
import ParkingLot.strategy.pricing.MotorBikePricingStrategy;
import ParkingLot.strategy.pricing.PricingStrategy;

public class PricingStrategyFactory {

    public PricingStrategyFactory() {
    }

    public static PricingStrategy getStrategy(VehicleType type) {
        return switch (type) {
            case CAR -> new CarPricingStrategy();
            case MOTORBIKE -> new MotorBikePricingStrategy();
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }
}
