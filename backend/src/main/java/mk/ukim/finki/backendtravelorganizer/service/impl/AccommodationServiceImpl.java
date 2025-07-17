package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
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

    public List<Accommodation> getAllAccommodations(String location, LocalDate checkInDate, LocalDate checkOutDate) {
        return accommodationRepository.findAvailable(location, checkInDate, checkOutDate);
    }

    public Accommodation createListing(String location, double costPerNight,String imageUrl) {
        Accommodation accommodation = new Accommodation(location, costPerNight, imageUrl);
        return accommodationRepository.save(accommodation);
    }

    public Accommodation editListing(Long id, String location, double costPerNight) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(AccommodationDoesNotExistException::new);
        accommodation.setLocation(location);
        accommodation.setCostPerNight(costPerNight);
        this.accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Accommodation getAccommodationById(Long id) {
        return accommodationRepository.findById(id).orElseThrow(AccommodationDoesNotExistException::new);
    }

    public Accommodation saveAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }

    public Accommodation addAccommodationToTrip(Long id, Long tripId, LocalDate checkIn, LocalDate checkOut) {
        Accommodation listing = accommodationRepository.findById(id)
                .orElseThrow(AccommodationDoesNotExistException::new);

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) throw new IllegalArgumentException("Invalid date range");

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(TripDoesNotExistException::new);

        boolean overlaps = listing.getBookings().stream().anyMatch(booking ->
                !(checkOut.isBefore(booking.getCheckInDate()) || checkIn.isAfter(booking.getCheckOutDate()))
        );
        if (overlaps) {
            throw new IllegalArgumentException("Selected dates overlap with an existing booking");
        }

        Accommodation booking = new Accommodation(
                listing.getLocation(),
                listing.getCostPerNight(),
                checkIn,
                checkOut,
                trip,
                listing.getImageUrl()
        );
        booking.setOriginalListing(listing);
        trip.addAccommodation(booking);
        return accommodationRepository.save(booking);
    }

    @Override
    public List<Accommodation> getAccommodationsByTripId(Long tripId) {
        return accommodationRepository.findByTripId(tripId);
    }

    @Override
    public List<Accommodation> getAllAvailableAccommodations() {
        return accommodationRepository.findAll();
    }


}
