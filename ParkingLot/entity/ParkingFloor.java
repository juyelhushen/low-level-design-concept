package ParkingLot.entity;

import ParkingLot.enums.SpotType;
import ParkingLot.observer.ParkingObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingFloor {

    private final String floorId;
    private final String floorName;

    // every spot on this floor, keyed by ID - used for admin lookups, not allocation
    private final Map<String, ParkingSpot> allSpots = new ConcurrentHashMap<>();

    // the actual allocation data structure: one queue of FREE spots per type.
    // allocateSpot() just polls this queue - O(1) - instead of scanning every
    // spot on the floor checking its status one by one. This is what keeps
    // entry-gate latency low even as a floor grows to hundreds of spots.
    private final Map<SpotType, ConcurrentLinkedDeque<ParkingSpot>> freePool = new ConcurrentHashMap<>();

    // Observer pattern's subscriber list. CopyOnWriteArrayList because reads
    // (notifying observers) happen on every single park/unpark, while writes
    // (a new observer subscribing) happen rarely - maybe once at startup.
    private final List<ParkingObserver> observers = new CopyOnWriteArrayList<>();

    public ParkingFloor(String floorId, String floorName) {
        this.floorId = floorId;
        this.floorName = floorName;
        for (SpotType type : SpotType.values()) {
            freePool.put(type, new ConcurrentLinkedDeque<>());
        }
    }

    public String getFloorId() {
        return floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void addSpot(ParkingSpot spot) {
        allSpots.put(spot.getSpotId(), spot);
        freePool.get(spot.getSpotType()).addLast(spot);
        notifyObservers(spot.getSpotType());
    }

    public void addObserver(ParkingObserver observer) {
        observers.add(observer);
    }

    /**
     * Pulls one free spot of the requested type and atomically claims it
     * for this vehicle. Two callers racing here will pull two DIFFERENT
     * spots off the deque (pollFirst is itself thread-safe), and then each
     * one's tryOccupy() call resolves any leftover race independently.
     */
    public ParkingSpot allocateSpot(Vehicle vehicle, SpotType type) {
        ConcurrentLinkedDeque<ParkingSpot> pool = freePool.get(type);
        ParkingSpot spot;
        while ((spot = pool.pollFirst()) != null) {
            if (spot.tryOccupy(vehicle)) {
                notifyObservers(type);
                return spot;
            }
            // spot was somehow already occupied (shouldn't normally happen since
            // this pool is the only place free spots live) - discard it and loop
            // to try the next one instead of handing a stale spot back to the caller
        }
        return null; // pool is empty - caller will try the next floor/spot type
    }

    public void releaseSpot(ParkingSpot spot) {
        spot.release();
        freePool.get(spot.getSpotType()).addLast(spot); // back into circulation
        notifyObservers(spot.getSpotType());
    }

    public int getAvailableCount(SpotType type) {
        return freePool.get(type).size();
    }

    // This is the "subject" half of the Observer pattern: the floor has no
    // idea what a DisplayBoard is, or how many observers exist. It just
    // shouts "spot status changed" and lets each listener decide what to do.
    private void notifyObservers(SpotType type) {
        for (ParkingObserver observer : observers) {
            observer.onSpotStatusChanged(this, type);
        }
    }
}
