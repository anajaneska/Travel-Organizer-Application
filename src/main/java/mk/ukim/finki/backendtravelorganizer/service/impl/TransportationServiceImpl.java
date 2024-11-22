package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.repository.TransportationRepository;
import mk.ukim.finki.backendtravelorganizer.repository.TripRepository;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;

import java.util.List;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;
    private final TripRepository tripRepository;

    public TransportationServiceImpl(TransportationRepository transportationRepository, TripRepository tripRepository) {
        this.transportationRepository = transportationRepository;
        this.tripRepository = tripRepository;
    }

    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }

    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow(() -> new RuntimeException("Transportation not found"));
    }

    public Transportation saveTransportation(Transportation transportation) {
        return transportationRepository.save(transportation);
    }

    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }

    @Override
    public Transportation addTransportationToTrip(Long tripId, Transportation transportation) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.addTransportation(transportation);
        transportationRepository.save(transportation);
        return transportation;
    }

    @Override
    public List<Transportation> getTransportationByTripId(Long tripId) {
        return transportationRepository.findByTripId(tripId);
    }
}
