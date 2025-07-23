package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationSearchDto;
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

//    public List<Transportation> getAllTransportations(TransportationSearchDto dto) {
//        return transportationRepository.findAvailableListingsWithEnoughSeats(dto.startLocation(), dto.destination(), dto.departureDate(), dto.wantedSeats());
//    }

    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow(TransportationDoesNotExistException::new);
    }

    public Transportation saveTransportation(Long id, TransportationCreateDto dto) {
        Transportation l = transportationRepository.findById(id)
                .orElseThrow(TransportationDoesNotExistException::new);

        l.setType(dto.type());
        l.setStartLocation(dto.startLocation());
        l.setDestination(dto.destination());

        l.setDepartureTime(dto.departureTime());
        l.setArrivalTime(dto.arrivalTime());

        l.setCost(dto.cost());
        return transportationRepository.save(l);
    }

    public void deleteTransportation(Long id) {
        transportationRepository.findById(id)
                .orElseThrow(TransportationDoesNotExistException::new);
        transportationRepository.deleteById(id);
    }

    public Transportation createListing(TransportationCreateDto dto) {
        Transportation l = new Transportation();
        l.setType(dto.type());
        l.setStartLocation(dto.startLocation());
        l.setDestination(dto.destination());

        l.setDepartureTime(dto.departureTime());
        l.setArrivalTime(dto.arrivalTime());

        l.setCost(dto.cost());
        return transportationRepository.save(l);
    }

    public Transportation bookTransport(Long id, Long tripId, TransportationBookingDto dto) {
        Transportation listing = transportationRepository.findById(id)
                .orElseThrow(TransportationDoesNotExistException::new);

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(TripDoesNotExistException::new);

        Transportation booking = new Transportation();
        booking.setType(listing.getType());
        booking.setStartLocation(listing.getStartLocation());
        booking.setDestination(listing.getDestination());
        booking.setDepartureTime(listing.getDepartureTime());
        booking.setArrivalTime(listing.getArrivalTime());
        booking.setCost(listing.getCost() * dto.wantedSeats());

        booking.setTrip(trip);

        trip.addTransportation(booking);

        return transportationRepository.save(booking);
    }

    private static Transportation getTransportation(TransportationBookingDto dto, Transportation listing, Trip trip) {
        Transportation b = new Transportation();

        b.setCost(listing.getCost() * dto.wantedSeats());
        b.setTrip(trip);

        b.setType(listing.getType());
        b.setStartLocation(listing.getStartLocation());
        b.setDestination(listing.getDestination());
        b.setDepartureTime(listing.getDepartureTime());
        b.setArrivalTime(listing.getArrivalTime());
        return b;
    }

//    @Override
//    public List<Transportation> getTransportationByTripId(Long tripId) {
//        return transportationRepository.findByTripId(tripId);
//    }

//    @Override
//    public Transportation uploadTicket(Long bookingId, MultipartFile file) {
//        Transportation booking = transportationRepository.findById(bookingId)
//                .orElseThrow(TransportationDoesNotExistException::new);
//
//        if (booking.getOriginalListing() == null)
//            throw new IllegalArgumentException("Cannot upload ticket to a listing");
//
//        booking.setTicketInfo(fileService.saveFile(file));
//        return transportationRepository.save(booking);
//    }
}
