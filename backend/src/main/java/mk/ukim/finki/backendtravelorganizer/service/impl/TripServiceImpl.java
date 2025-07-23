package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.*;
import mk.ukim.finki.backendtravelorganizer.model.dto.TripCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TripDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.*;
import mk.ukim.finki.backendtravelorganizer.repository.*;
import mk.ukim.finki.backendtravelorganizer.service.TripService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final AccommodationRepository accommodationRepository;
    private final ActivityRepository  activityRepository;
    private final TransportationRepository transportationRepository;
    private final UserRepository userRepository;

    public TripServiceImpl(TripRepository tripRepository, AccommodationRepository accommodationRepository, ActivityRepository activityRepository, TransportationRepository transportationRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.accommodationRepository = accommodationRepository;
        this.activityRepository = activityRepository;
        this.transportationRepository = transportationRepository;
        this.userRepository = userRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public Trip editTrip(Long id, TripDto dto) {
        Trip trip = this.tripRepository.findById(id)
                .orElseThrow(TripDoesNotExistException::new);

        trip.setName(dto.getDestination());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setBudget(dto.getBudget());
        trip.setCurrentExpenses(dto.getCurrentExpenses());

        if (dto.getAccommodations() != null) {
            trip.getAccommodations().clear();
            dto.getAccommodations().forEach(accommodationDto -> {
                Accommodation accommodation = accommodationRepository.findById(accommodationDto.getId())
                        .orElseThrow(AccommodationDoesNotExistException::new);
                trip.addAccommodation(accommodation);
            });
        }
        if (dto.getActivities() != null) {
            trip.getActivities().clear();
            dto.getActivities().forEach(activityDto -> {
                Activity activity = activityRepository.findById(activityDto.getId())
                        .orElseThrow(ActivityDoesNotExistException::new);
                trip.addActivity(activity);
            });
        }
        if (dto.getTransportations() != null) {
            trip.getTransportations().clear();
            dto.getTransportations().forEach(transportationDto -> {
                Transportation transportation = transportationRepository.findById(transportationDto.getId())
                        .orElseThrow(TransportationDoesNotExistException::new);
                trip.addTransportation(transportation);
            });
        }
        return tripRepository.save(trip);
    }

    @Override
    public Trip createTrip(TripCreateDto dto, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException();
        }

        Trip trip = new Trip();
        trip.setName(dto.getName());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setBudget(dto.getBudget());
        trip.setUser(user);

        return tripRepository.save(trip);
    }
    public List<Trip> getTripsByUser(String username) {
        return tripRepository.findByUserUsername(username);
    }
}
