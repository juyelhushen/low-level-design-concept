package ParkingLot.service;

import ParkingLot.entity.ParkingTicket;
import ParkingLot.entity.Payment;
import ParkingLot.entity.Vehicle;
import ParkingLot.enums.PaymentMode;

// The entire public API of the lot, deliberately kept to two operations -
// a real parking lot only ever does two things: let a vehicle in, let a
// vehicle out. Pricing, payment, which spot, which floor - all of that
// is an implementation detail hidden behind these two methods.

public interface ParkingLotService {
    ParkingTicket parkVehicle(Vehicle vehicle);

    Payment unparkVehicle(String ticketId, PaymentMode mode);
}