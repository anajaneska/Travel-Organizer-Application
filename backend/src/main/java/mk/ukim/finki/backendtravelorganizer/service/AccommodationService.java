package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationDto;
import java.util.List;

public interface AccommodationService {
    List<Accommodation> getAllAccommodations();
    Accommodation getAccommodationById(Long id);
    Accommodation saveAccommodation(Long tripId,Accommodation accommodation);
    Accommodation editAccommodation(Long id, AccommodationDto dto);
    void deleteAccommodation(Long id);
    List<Accommodation> getAccommodationsByTripId(Long tripId);
    Accommodation addAccommodationToTrip(Long tripId, AccommodationDto dto);
}
