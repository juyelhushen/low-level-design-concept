package ParkingLot.entity;

import ParkingLot.context.FeeCalculationContext;
import ParkingLot.context.PaymentStrategyContext;
import ParkingLot.observer.DisplayBoard;
import ParkingLot.repository.InMemoryParkingSpotRepository;
import ParkingLot.repository.InMemoryParkingTicketRepository;
import ParkingLot.service.ParkingLotServerImpl;
import ParkingLot.service.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public final class ParkingLot {

    // ---- Why this class exists at all ----
    // A parking lot is physically singular: there is exactly one set of
    // floors, exactly one pool of spots, exactly one source of truth for
    // "is this spot free right now." If two separate ParkingLot objects
    // existed in the same app, each with its own copy of floors/spots,
    // they could both think the same physical spot is free and hand it
    // to two different cars - a real double-booking bug, not a theoretical
    // one. Singleton guarantees there is only ever one of these.

    // every floor this lot owns - the SAME list object is handed to
    // ParkingLotServiceImpl, so adding a floor here is immediately visible
    // to allocation logic without any extra wiring
    private final List<ParkingFloor> floors = new ArrayList<>();

    // the lot owns exactly one instance of each dependency. In a Spring
    // app these would be @Beans injected instead of "new'd" here, but the
    // ownership idea is identical: one shared instance, not one per request.
    private final InMemoryParkingSpotRepository spotRepository = new InMemoryParkingSpotRepository();
    private final InMemoryParkingTicketRepository ticketRepository = new InMemoryParkingTicketRepository();
    private final FeeCalculationContext feeCalculationService = new FeeCalculationContext();
    private final PaymentStrategyContext paymentService = new PaymentStrategyContext();
    private final DisplayBoard displayBoard = new DisplayBoard();

    // built lazily (see getParkingLotService below) instead of in the
    // constructor, because at construction time the floors list is still
    // empty - nobody's called addFloor() yet
    private ParkingLotService parkingLotService;

    // ---- The Singleton mechanics ----

    // PRIVATE constructor: this is the line that actually enforces "only
    // one instance can ever exist." No other class can write
    // `new ParkingLot()` - the compiler won't let them, because the
    // constructor isn't visible outside this file.
    private ParkingLot() {
    }

    // The "initialization-on-demand holder" idiom. Instead of a static
    // field initialized directly on ParkingLot (which runs the moment the
    // class loads, even if nobody ever calls getInstance()), the actual
    // instance lives on this separate nested class.
    //
    // Why this matters: the JVM only loads a class the FIRST time it's
    // referenced. Holder.INSTANCE is only referenced inside getInstance(),
    // so the Holder class - and therefore the ParkingLot instance inside
    // it - doesn't get created until someone actually calls getInstance().
    // That's the "lazy" part.
    //
    // The "thread-safe" part is free: the JVM spec already guarantees
    // class loading and static initialization happen exactly once, with
    // proper synchronization, even if 50 threads reference Holder at the
    // same instant. So we get a lazy, thread-safe singleton WITHOUT
    // writing a single `synchronized` keyword - which is exactly the kind
    // of detail worth calling out over the classic (and slower, more
    // error-prone) double-checked-locking singleton.
    private static class Holder {
        private static final ParkingLot INSTANCE = new ParkingLot();
    }

    // the only way anyone gets a ParkingLot. Every call, from anywhere in
    // the app, returns the exact same object.
    public static ParkingLot getInstance() {
        return Holder.INSTANCE;
    }

    // ---- Operations on the singleton ----

    public void addFloor(ParkingFloor floor) {
        // wire the Observer relationship right here, at the moment the
        // floor joins the lot - the floor itself never needs to know
        // a DisplayBoard exists, it just calls notifyObservers() and
        // whoever's subscribed (the board, today) reacts
        floor.addObserver(displayBoard);
        floors.add(floor);

        // invalidate the cached service so the NEXT call to
        // getParkingLotService() rebuilds it with the updated floor list -
        // otherwise a service built before this floor was added would
        // never see it
        parkingLotService = null;
    }

    public ParkingLotService getParkingLotService() {
        // build-once-then-cache: cheap to call repeatedly (every EntryGate
        // and ExitGate call goes through this), but still picks up new
        // floors because addFloor() resets the cache above
        if (parkingLotService == null) {
            parkingLotService = new ParkingLotServerImpl(
                    floors,paymentService,feeCalculationService,spotRepository,ticketRepository);
        }
        return parkingLotService;
    }

    public List<ParkingFloor> getFloors() {
        return List.copyOf(floors); // defensive copy - callers can't mutate our internal list
    }
}
