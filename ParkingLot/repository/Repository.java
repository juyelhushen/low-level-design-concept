package ParkingLot.repository;

import ParkingLot.entity.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void save(T t);
    Optional<T> findById(String id);
    List<T> findAll();
}
