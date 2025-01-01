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
        return accommodationRepository.findById(id).orElseThrow(() -> new AccommodationDoesNotExistException());
    }

    public Accommodation saveAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }
    public Accommodation addAccommodationToTrip(Long tripId, Accommodation accommodation) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripDoesNotExistException());
        trip.addAccommodation(accommodation);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    @Override
    public List<Accommodation> getAccommodationsByTripId(Long tripId) {
        return accommodationRepository.findByTripId(tripId);
    }
    /*
    @Override
    @Transactional
    public Optional<Accommodation> editAccommodation(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        Host host  = this.hostRepository.findById(hostId)
                .orElseThrow(()->new HostNotFoundException(hostId));

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setNumRooms(numRooms);
        accommodation.setHost(host);

        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> editAccommodation(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        Host host  = this.hostRepository.findById(accommodationDto.getHost())
                .orElseThrow(()->new HostNotFoundException(accommodationDto.getHost()));
        accommodation.setName(accommodation.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setHost(host);
        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double cost;
     */

    @Override
    public Accommodation editAccommodation(Long id, AccommodationDto dto) {
        Trip trip = this.tripRepository.findById(dto.getTrip())
                .orElseThrow(()->new TripDoesNotExistException());
        Accommodation accommodation = new Accommodation(dto.getLocation(), dto.getCheckInDate(), dto.getCheckOutDate(), dto.getCost(), trip);
        this.accommodationRepository.save(accommodation);
        return accommodation;
    }
}
