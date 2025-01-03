package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationDto;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> getAllAccommodations();
    Accommodation getAccommodationById(Long id);
    Accommodation saveAccommodation(Accommodation accommodation);
    void deleteAccommodation(Long id);
    Accommodation addAccommodationToTrip(Long tripId, Accommodation accommodation);
    List<Accommodation> getAccommodationsByTripId(Long tripId);
    Accommodation editAccommodation(Long id, AccommodationDto dto);
}
