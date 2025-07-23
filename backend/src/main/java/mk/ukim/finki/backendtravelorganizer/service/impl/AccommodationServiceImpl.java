package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.AccommodationDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.repository.AccommodationRepository;
import mk.ukim.finki.backendtravelorganizer.repository.TripRepository;
import mk.ukim.finki.backendtravelorganizer.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final TripRepository tripRepository;


    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, TripRepository tripRepository) {
        this.accommodationRepository = accommodationRepository;
        this.tripRepository = tripRepository;
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Accommodation getAccommodationById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(AccommodationDoesNotExistException::new);
    }

    public Accommodation saveAccommodation(Long tripId, Accommodation accommodation) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(TripDoesNotExistException::new);
        accommodation.setTrip(trip);
        return accommodationRepository.save(accommodation);
    }
    public Accommodation editAccommodation(Long id, AccommodationDto dto) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(AccommodationDoesNotExistException::new);
        accommodation.setLocation(dto.getLocation());
        accommodation.setCheckInDate(dto.getCheckInDate());
        accommodation.setCheckOutDate(dto.getCheckOutDate());
        accommodation.setTotalCost(dto.getTotalCost());
        return accommodationRepository.save(accommodation);
    }

    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public List<Accommodation> getAccommodationsByTripId(Long tripId) {
        return accommodationRepository.findByTripId(tripId);
    }
    public Accommodation addAccommodationToTrip(Long tripId, AccommodationDto dto) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripDoesNotExistException());

        Accommodation accommodation = new Accommodation(
                dto.getLocation(),
                dto.getCheckInDate(),
                dto.getCheckOutDate(),
                trip,
                dto.getTotalCost()
        );

        return accommodationRepository.save(accommodation);
    }

}
