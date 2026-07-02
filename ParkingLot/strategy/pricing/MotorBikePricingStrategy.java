package ParkingLot.strategy.pricing;

import ParkingLot.entity.ParkingTicket;

public class MotorBikePricingStrategy implements PricingStrategy {

    private static final double HOURLY_RATE = 30.0;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long minutes = Math.max(1, ticket.getParkedDuration().toMinutes());
        long hours = (long) Math.ceil(minutes / 60.0);
        return hours * HOURLY_RATE;
    }

}
