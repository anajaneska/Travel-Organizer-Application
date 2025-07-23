package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {
    List<Accommodation> getAllAccommodations();
    Accommodation getAccommodationById(Long id);
    Accommodation saveAccommodation(Long tripId,Accommodation accommodation);
    Accommodation editAccommodation(Long id, String location, LocalDate checkIn, LocalDate checkOut, Double totalCost);
    void deleteAccommodation(Long id);
    List<Accommodation> getAccommodationsByTripId(Long tripId);
}
