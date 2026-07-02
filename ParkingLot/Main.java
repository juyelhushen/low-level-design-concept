package ParkingLot;

import ParkingLot.entity.*;
import ParkingLot.enums.PaymentMode;
import ParkingLot.enums.SpotType;
import ParkingLot.enums.VehicleType;
import ParkingLot.exception.NoSpotAvailableException;
import ParkingLot.factory.ParkingSpotFactory;
import ParkingLot.factory.VehicleFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // getInstance() always returns the SAME ParkingLot - call it again
        // anywhere else in the app and you get this exact object back
        ParkingLot parkingLot = ParkingLot.getInstance();

        ParkingFloor groundFloor = new ParkingFloor("F1", "Ground Floor");
        groundFloor.addSpot(ParkingSpotFactory.create(SpotType.COMPACT, "F1-C1", "F1"));
        groundFloor.addSpot(ParkingSpotFactory.create(SpotType.COMPACT, "F1-C2", "F1"));
        groundFloor.addSpot(ParkingSpotFactory.create(SpotType.MOTORBIKE, "F1-M1", "F1"));
        parkingLot.addFloor(groundFloor); // wires DisplayBoard as an observer of this floor

        ParkingFloor firstFloor = new ParkingFloor("F2", "First Floor");
        firstFloor.addSpot(ParkingSpotFactory.create(SpotType.COMPACT, "F2-C1", "F2"));
        parkingLot.addFloor(firstFloor);

        EntryGate entryGate = new EntryGate("Entry-1", parkingLot);
        ExitGate exitGate = new ExitGate("Exit-1", parkingLot);

        System.out.println("--- Vehicles entering ---");
        Vehicle car = VehicleFactory.create(VehicleType.CAR, "KA-01-AB-1234");
        Vehicle bike = VehicleFactory.create(VehicleType.MOTORBIKE, "KA-05-XY-9999");

        ParkingTicket carTicket = entryGate.issueTicket(car);   // -> F1-C1 (Compact)
        entryGate.issueTicket(bike);                            // -> F1-M1 (Motorbike)

        Thread.sleep(1500); // simulate parked time so the fee isn't zero

        System.out.println("--- Vehicles exiting ---");
        exitGate.processExit(carTicket.getTicketId(), PaymentMode.UPI);
        exitGate.processExit(carTicket.getTicketId(), PaymentMode.CARD);

        System.out.println("--- Draining ground floor's compact + spare large spot ---");
        entryGate.issueTicket(VehicleFactory.create(VehicleType.CAR, "KA-01-CC-0001")); // -> F1-C2
        entryGate.issueTicket(VehicleFactory.create(VehicleType.CAR, "KA-01-CC-0002")); // -> F1-C1 (freed above)
        entryGate.issueTicket(VehicleFactory.create(VehicleType.CAR, "KA-01-CC-0003")); // compact exhausted -> takes F1-L1

        System.out.println("--- Truck overflow + full-lot failure path ---");
        // F1-L1 just got taken by the car above, so this truck overflows
        // onto the first floor's only LARGE spot instead of failing

        // no LARGE spot left anywhere now -> allocation fails on purpose
        try {
            entryGate.issueTicket(VehicleFactory.create(VehicleType.CAR, "KA-09-TT-4322"));
        } catch (NoSpotAvailableException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }
}
