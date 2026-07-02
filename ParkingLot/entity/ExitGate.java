package ParkingLot.entity;

import ParkingLot.enums.PaymentMode;

public class ExitGate {

    private final String gateId;
    private final ParkingLot parkingLot;

    public ExitGate(String gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public Payment processExit(String ticketId, PaymentMode mode) {
        Payment payment = parkingLot.getParkingLotService().unparkVehicle(ticketId, mode);
        System.out.printf("[%s] Ticket %s closed. Charged Rs.%.2f via %s. Status: %s%n",
                gateId, ticketId, payment.getAmount(), mode, payment.getStatus());
        return payment;
    }

}
