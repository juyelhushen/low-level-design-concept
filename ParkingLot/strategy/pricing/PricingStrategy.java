package ParkingLot.strategy.pricing;

import ParkingLot.entity.ParkingTicket;

public interface PricingStrategy {

    double calculateFee(ParkingTicket ticket);

}
