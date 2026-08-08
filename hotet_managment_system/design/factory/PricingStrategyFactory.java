package hotet_managment_system.design.factory;

import hotet_managment_system.design.strategy.pricing.PricingStrategy;
import hotet_managment_system.design.strategy.pricing.SeasonalPricingStrategy;
import hotet_managment_system.design.strategy.pricing.WeekendPricingStrategy;

import java.time.LocalDate;
import java.time.Month;

public final class PricingStrategyFactory {

    private PricingStrategyFactory(){}

    // In a real system this might look at more signals (events, hotel-specific rules).
    // For the interview: month-based heuristic is enough to show the pattern.
    public static PricingStrategy getStrategy(LocalDate checkIn) {
        boolean isPeakSeason = checkIn.getMonth() == Month.DECEMBER
                || checkIn.getMonth() == Month.JANUARY;

        if (isPeakSeason) {
            return SeasonalPricingStrategy.INSTANCE;
        }

        return WeekendPricingStrategy.INSTANCE; // applies weekend uplift on relevant nights
    }
}
