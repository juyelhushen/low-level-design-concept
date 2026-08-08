package hotet_managment_system.design.strategy.pricing;

import hotet_managment_system.entity.Room;

import java.time.LocalDate;

public class StandardPricingStrategy implements PricingStrategy {

    public static final StandardPricingStrategy INSTANCE = new StandardPricingStrategy();

    private StandardPricingStrategy() {};

    @Override
    public double pricePerNight(Room room, LocalDate date) {
        return room.getMaxPricePerNight();
    }
}
