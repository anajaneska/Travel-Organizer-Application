package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Expense;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TransportationDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.repository.TransportationRepository;
import mk.ukim.finki.backendtravelorganizer.repository.TripRepository;
import mk.ukim.finki.backendtravelorganizer.service.FileService;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;
    private final TripRepository tripRepository;
    private final FileService fileService;

    public TransportationServiceImpl(TransportationRepository transportationRepository, TripRepository tripRepository, FileService fileService) {
        this.transportationRepository = transportationRepository;
        this.tripRepository = tripRepository;
        this.fileService = fileService;
    }

    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }

    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow(() -> new TransportationDoesNotExistException());
    }

    public Transportation saveTransportation(Transportation transportation) {
        return transportationRepository.save(transportation);
    }

    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }

    @Override
    public Transportation addTransportationToTrip(Long tripId, Transportation transportation) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new TripDoesNotExistException());
        trip.addTransportation(transportation);
        transportationRepository.save(transportation);
        return transportation;
    }

    @Override
    public List<Transportation> getTransportationByTripId(Long tripId) {
        return transportationRepository.findByTripId(tripId);
    }

    @Override
    public Transportation uploadTicket(Long transportationId, MultipartFile file) {
        Transportation transportation = transportationRepository.findById(transportationId)
                .orElseThrow(() -> new TransportationDoesNotExistException());

        String filePath = fileService.saveFile(file);
        transportation.setTicketInfo(filePath);

        return transportationRepository.save(transportation);
    }

    @Override
    public Transportation editTransportation(Long id, TransportationDto dto) {
        Trip trip = this.tripRepository.findById(dto.getTrip())
                .orElseThrow(()->new TripDoesNotExistException());
        Transportation transportation = new Transportation(dto.getType(), dto.getStartLocation(), dto.getDestination(), dto.getTicketInfo(), dto.getDepartureTime(), dto.getArrivalTime(),dto.getCost() ,trip);
        this.transportationRepository.save(transportation);
        return transportation;
    }
}
