package ParkingLot.context;

import ParkingLot.entity.ParkingTicket;
import ParkingLot.strategy.pricing.PricingStrategy;


/**
 * Classic GoF Strategy Context: holds ONE active strategy at a time,
 * swappable at runtime via setStrategy(). Unlike the map-based version,
 * the CALLER decides which strategy to plug in before calling
 * calculateFee() - this class has zero knowledge of vehicle types.
 */
public class FeeCalculationContext {
    private PricingStrategy strategy;


    public FeeCalculationContext() {
    }

    // constructor injection for the common case - start with a known strategy
    public FeeCalculationContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    // the setter IS the "strategy swap" - this is the line that makes it
    // Strategy pattern rather than just an interface reference: the
    // context's behavior can change at runtime without touching its code
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFee(ParkingTicket ticket) {
        return strategy.calculateFee(ticket); // delegates - never branches on vehicle type itself
    }
}
