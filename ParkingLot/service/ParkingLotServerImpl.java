package ParkingLot.service;

import ParkingLot.context.FeeCalculationContext;
import ParkingLot.context.PaymentStrategyContext;
import ParkingLot.entity.*;
import ParkingLot.enums.PaymentMode;
import ParkingLot.enums.SpotType;
import ParkingLot.enums.TicketStatus;
import ParkingLot.exception.InvalidTicketException;
import ParkingLot.exception.NoSpotAvailableException;
import ParkingLot.factory.PaymentStrategyFactory;
import ParkingLot.factory.PricingStrategyFactory;
import ParkingLot.repository.InMemoryParkingSpotRepository;
import ParkingLot.repository.InMemoryParkingTicketRepository;
import ParkingLot.strategy.payment.PaymentStrategy;
import ParkingLot.strategy.pricing.PricingStrategy;

import java.util.List;

public class ParkingLotServerImpl implements ParkingLotService {

    private final List<ParkingFloor> floors;
    private final PaymentStrategyContext paymentStrategyContext;
    private final FeeCalculationContext feeCalculationContext;
    private final InMemoryParkingSpotRepository parkingSpotRepository;
    private final InMemoryParkingTicketRepository parkingTicketRepository;

    public ParkingLotServerImpl(List<ParkingFloor> floors,
                                PaymentStrategyContext paymentStrategyContext,
                                FeeCalculationContext feeCalculationContext,
                                InMemoryParkingSpotRepository parkingSpotRepository,
                                InMemoryParkingTicketRepository parkingTicketRepository) {
        this.floors = floors;
        this.paymentStrategyContext = paymentStrategyContext;
        this.feeCalculationContext = feeCalculationContext;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }


    @Override
    public ParkingTicket parkVehicle(Vehicle vehicle) {

        for (ParkingFloor floor : floors) {
            for (SpotType type : vehicle.getCompatibleSpot()) {
                ParkingSpot spot = floor.allocateSpot(vehicle, type);

                if (spot != null) {
                    parkingSpotRepository.save(spot);
                    ParkingTicket ticket = new ParkingTicket(vehicle, spot);
                    parkingTicketRepository.save(ticket);
                    return ticket;
                }

            }
        }

        // every floor, every compatible type, came back empty - genuinely full
        throw new NoSpotAvailableException(
                "No spot available for vehicle type: " + vehicle.getVehicleType());
    }

    @Override
    public Payment unparkVehicle(String ticketId, PaymentMode mode) {
        ParkingTicket ticket = parkingTicketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSpotAvailableException("Ticket not found : " + ticketId));

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new InvalidTicketException("Ticket already closed : "+ ticketId);
        }

        ticket.close();

        PricingStrategy strategy = PricingStrategyFactory.getStrategy(ticket.getVehicle().getVehicleType());
        feeCalculationContext.setStrategy(strategy);
        double fee = feeCalculationContext.calculateFee(ticket);

        ParkingFloor floor = findFloor(ticket.getFloorId());
        floor.releaseSpot(ticket.getSpot());

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(mode);
        paymentStrategyContext.setPaymentStrategy(paymentStrategy);
        return paymentStrategyContext.processPayment(ticketId, fee,mode);

    }

    private ParkingFloor findFloor(String floorId) {
        return floors.stream().filter(floor -> floor.getFloorId().equals(floorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No floor is available"));
    }
}
