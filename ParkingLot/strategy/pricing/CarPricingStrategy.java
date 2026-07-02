package ParkingLot.strategy.pricing;

import ParkingLot.entity.ParkingTicket;

public class CarPricingStrategy implements PricingStrategy {

    private static final double FLAT_RATE_FIRST_HOUR = 40.0;
    private static final double HOURLY_RATE_AFTER = 30.0;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long minutes = Math.max(1, ticket.getParkedDuration().toMinutes()); // never charge for 0 minutes
        long hours = (long) Math.ceil(minutes / 60.0); // round UP - 61 minutes bills as 2 hours
        if (hours <= 1) {
            return FLAT_RATE_FIRST_HOUR;
        }
        return FLAT_RATE_FIRST_HOUR + (hours - 1) * HOURLY_RATE_AFTER;
    }


}
