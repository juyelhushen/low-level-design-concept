package ParkingLot.entity;

public class EntryGate {
    private final String gateId;
    private final ParkingLot parkingLot; // every gate talks to the SAME singleton instance

    public EntryGate(String gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        // delegates straight through to the service - the gate itself has
        // zero allocation logic, it's just a thin façade with a print statement
        ParkingTicket ticket = parkingLot.getParkingLotService().parkVehicle(vehicle);
        System.out.printf("[%s] Issued ticket %s to %s (%s) -> spot %s%n",
                gateId, ticket.getTicketId(), vehicle.getLicensePlate(),
                vehicle.getVehicleType(), ticket.getSpot().getSpotId());
        return ticket;
    }
}
