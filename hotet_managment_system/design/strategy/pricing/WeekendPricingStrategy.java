package hotet_managment_system.design.strategy.pricing;

import hotet_managment_system.entity.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendPricingStrategy implements PricingStrategy {

    public static final WeekendPricingStrategy INSTANCE = new WeekendPricingStrategy();

    private WeekendPricingStrategy(){}

    private static final double WEEKEND_MULTIPLIER = 1.2;


    @Override
    public double pricePerNight(Room room, LocalDate date) {
        boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        return isWeekend ? room.getMaxPricePerNight() * WEEKEND_MULTIPLIER
                : room.getMaxPricePerNight();
    }
}
