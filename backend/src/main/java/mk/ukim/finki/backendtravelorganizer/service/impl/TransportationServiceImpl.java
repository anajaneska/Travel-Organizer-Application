package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TransportationDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
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

    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow(TransportationDoesNotExistException::new);
    }
    @Override
    public List<Transportation> getTransportationByTripId(Long tripId) {
        return transportationRepository.findByTripId(tripId);
    }

    public Transportation saveTransportation(Long tripId, TransportationDto dto) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(TripDoesNotExistException::new);

        Transportation transport = new Transportation();
        transport.setType(dto.getType());
        transport.setStartLocation(dto.getStartLocation());
        transport.setDestination(dto.getDestination());
        transport.setDepartureTime(dto.getDepartureTime());
        transport.setArrivalTime(dto.getArrivalTime());
        transport.setCost(dto.getCost());
        transport.setTrip(trip);

        return transportationRepository.save(transport);
    }
    public Transportation editTransportation(Long id, TransportationDto dto) {
        Transportation transport = transportationRepository.findById(id)
                .orElseThrow(TransportationDoesNotExistException::new);

        transport.setType(dto.getType());
        transport.setStartLocation(dto.getStartLocation());
        transport.setDestination(dto.getDestination());
        transport.setDepartureTime(dto.getDepartureTime());
        transport.setArrivalTime(dto.getArrivalTime());
        transport.setCost(dto.getCost());

        return transportationRepository.save(transport);
    }
    public void deleteTransportation(Long id) {
        transportationRepository.findById(id)
                .orElseThrow(TransportationDoesNotExistException::new);
        transportationRepository.deleteById(id);
    }
    public Transportation addTransportationToTrip(Long tripId, TransportationDto dto) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripDoesNotExistException());

        Transportation transportation = new Transportation();
        transportation.setType(dto.getType());
        transportation.setStartLocation(dto.getStartLocation());
        transportation.setDestination(dto.getDestination());
        transportation.setDepartureTime(dto.getDepartureTime());
        transportation.setArrivalTime(dto.getArrivalTime());
        transportation.setCost(dto.getCost());
        transportation.setTrip(trip);

        return transportationRepository.save(transportation);
    }
}
