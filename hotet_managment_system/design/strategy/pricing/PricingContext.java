package hotet_managment_system.design.strategy.pricing;

import hotet_managment_system.entity.DateRange;
import hotet_managment_system.entity.Room;

import java.time.LocalDate;

public class PricingContext {

    private PricingStrategy pricingStrategy;

    public PricingContext(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public double calculateTotal(Room room, DateRange dateRange) {
        double total = 0;
        LocalDate current = dateRange.checkIn();
        while(current.isBefore(dateRange.checkOut())) {
            total += pricingStrategy.pricePerNight(room, current);
            current = current.plusDays(1);
        }
        return total;
    }
}
