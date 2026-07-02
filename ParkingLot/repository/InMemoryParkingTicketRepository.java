package ParkingLot.repository;

import ParkingLot.entity.ParkingTicket;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryParkingTicketRepository implements Repository<ParkingTicket> {

    private Map<String, ParkingTicket> inMemo = new ConcurrentHashMap<>();

    @Override
    public void save(ParkingTicket ticket) {
        inMemo.put(ticket.getTicketId(), ticket);
    }

    @Override
    public Optional<ParkingTicket> findById(String id) {
        return Optional.ofNullable(inMemo.get(id));
    }

    @Override
    public List<ParkingTicket> findAll() {
        return List.copyOf(inMemo.values());
    }
}
