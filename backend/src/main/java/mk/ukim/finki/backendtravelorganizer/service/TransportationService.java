package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationSearchDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface TransportationService {
    List<Transportation> getAllTransportations(TransportationSearchDto dto);
    Transportation createListing(TransportationCreateDto dto);
    Transportation bookTransport(Long listingId, Long tripId, TransportationBookingDto dto);
    Transportation getTransportationById(Long id);
    Transportation saveTransportation(Long id, TransportationCreateDto dto);
    void deleteTransportation(Long id);
    List<Transportation> getTransportationByTripId(Long tripId);
    Transportation uploadTicket(Long transportationId, MultipartFile file);
}
