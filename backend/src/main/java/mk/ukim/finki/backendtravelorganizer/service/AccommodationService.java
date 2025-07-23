package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {
    //List<Accommodation> getAllAccommodations(String location, LocalDate checkIn, LocalDate checkOut);
    //Accommodation createListing(String location, double costPerNight, String imageUrl);
    Accommodation editListing(Long id, String location, double pricePerNight);
    Accommodation getAccommodationById(Long id);
    Accommodation saveAccommodation(Accommodation accommodation);
    void deleteAccommodation(Long id);
    Accommodation addAccommodationToTrip(Long id, Long tripId, LocalDate checkIn, LocalDate checkOut, Double totalCost);
    List<Accommodation> getAccommodationsByTripId(Long tripId);

    List<Accommodation> getAllAvailableAccommodations();
}
