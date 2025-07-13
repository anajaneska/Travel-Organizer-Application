package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.TripDto;

import java.util.List;
import java.util.Optional;

public interface TripService {
    List<Trip> getAllTrips();
    Optional<Trip> getTripById(Long id);
    Trip saveTrip(Trip trip);
    void deleteTrip(Long id);
    Trip editTrip(Long id, TripDto dto);
}
