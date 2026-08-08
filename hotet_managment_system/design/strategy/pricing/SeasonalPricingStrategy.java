package hotet_managment_system.design.strategy.pricing;

import hotet_managment_system.entity.Room;

import java.time.LocalDate;
import java.time.Month;

public class SeasonalPricingStrategy implements PricingStrategy {

    public static final SeasonalPricingStrategy INSTANCE = new SeasonalPricingStrategy();
    private SeasonalPricingStrategy() {}

    private static final double PEAK_MULTIPLIER = 1.5;

    @Override
    public double pricePerNight(Room room, LocalDate date) {
        boolean isPeakMonth = date.getMonth() == Month.DECEMBER
                || date.getMonth() == Month.JANUARY;

        return isPeakMonth
                ? room.getMaxPricePerNight() * PEAK_MULTIPLIER
                : room.getMaxPricePerNight();
    }
}
